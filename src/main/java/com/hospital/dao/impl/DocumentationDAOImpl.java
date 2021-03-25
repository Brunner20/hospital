package com.hospital.dao.impl;

import com.hospital.dao.DAOException;
import com.hospital.dao.DocumentationDAO;
import com.hospital.dao.connection.ConnectionPool;
import com.hospital.dao.connection.ConnectionPoolException;
import com.hospital.dao.connection.PoolProvider;
import com.hospital.entity.Appointment;
import com.hospital.entity.AppointmentInfo;
import com.hospital.entity.AppointmentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentationDAOImpl implements DocumentationDAO {

    private static final String INSERT_APPOINTMENT = "insert into hospital.patient_appointments(date_of_completion,date_of_appointment," +
            "id_patient,id_appointment,id_executor,status,id_staff_appoint ) VALUES (?,?,?,?,?,?,?)";

    private static final String SELECT_APPOINTMENT_INFO = "select * from hospital.appointments WHERE title = ? and type = ? ";
    private static final String SELECT_APPOINTMENT_INFO_BY_ID = "select * from hospital.appointments WHERE id = ? ";
    private static final String INSERT_APPOINTMENT_INFO = "insert into hospital.appointments(title ,type) values (?,?)";
    private static final String SELECT_APPOINTMENT_BY_PATIENT = "select * from hospital.patient_appointments where id_patient =?";

    private final ConnectionPool connectionPool = PoolProvider.getConnectionPool();

    @Override
    public void addAppointment(Appointment appointment) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_APPOINTMENT);
            preparedStatement.setDate(1,appointment.getDateOfCompletion());
            preparedStatement.setDate(2,appointment.getDateOfAppointment());
            preparedStatement.setLong(3,appointment.getPatientId());
            preparedStatement.setLong(4,appointment.getInfoId());
            preparedStatement.setLong(5,appointment.getExecuteStaffId());
            preparedStatement.setLong(6,appointment.getStatus().getId());
            preparedStatement.setLong(7,appointment.getAppointingDoctorId());
            preparedStatement.execute();

                //TODO внести назначение в историю болезней(транзакция?)
        }  catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            }catch (SQLException e){
                throw new DAOException("Close preparedStatement error ", e);
            }
        }

    }

    @Override
    public AppointmentInfo getAppointmentInfo(String title, AppointmentType type) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        AppointmentInfo appointmentInfo = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_INFO);
            preparedStatement.setString(1,title);
            preparedStatement.setLong(2,type.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                appointmentInfo = new AppointmentInfo();
                appointmentInfo.setId(resultSet.getLong(1));
                appointmentInfo.setInfo(resultSet.getString(2));
                appointmentInfo.setType(resultSet.getInt(3));
            }else{
                 appointmentInfo = insertAppointmentInfo(title,type);
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return appointmentInfo;
    }

    @Override
    public List<Appointment> getAllAppointmentsByPatientId(long patientId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Appointment> appointmentsByPatient = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_BY_PATIENT);
            preparedStatement.setLong(1,patientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(resultSet.getLong(1));
                appointment.setDateOfAppointment(resultSet.getDate(2));
                appointment.setDateOfAppointment(resultSet.getDate(3));
                appointment.setInfoId(resultSet.getLong(4));
                appointment.setInfoId(resultSet.getLong(5));
                appointment.setExecuteStaffId(resultSet.getLong(6));
                appointment.setStatus(resultSet.getInt(7));
                appointment.setAppointingDoctorId(resultSet.getInt(8));
                appointmentsByPatient.add(appointment);
            }
        } catch (SQLException | ConnectionPoolException throwables) {
            throw new DAOException(throwables);
        }finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            }catch (SQLException e){
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return appointmentsByPatient;
    }

    @Override
    public AppointmentInfo getAppointmentInfoById(long infoId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        AppointmentInfo appointmentInfo = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_INFO_BY_ID);
            preparedStatement.setLong(1,infoId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                appointmentInfo = new AppointmentInfo();
                appointmentInfo.setId(resultSet.getLong(1));
                appointmentInfo.setInfo(resultSet.getString(2));
                appointmentInfo.setType(resultSet.getInt(3));
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return appointmentInfo;
    }

    private AppointmentInfo insertAppointmentInfo(String title, AppointmentType type) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatementForInsert = null;
        PreparedStatement preparedStatementForSelect = null;
        AppointmentInfo  appointmentInfo = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatementForInsert = connection.prepareStatement(INSERT_APPOINTMENT_INFO);
            preparedStatementForInsert.setString(1,title);
            preparedStatementForInsert.setLong(2,type.getId());
            preparedStatementForInsert.execute();

            preparedStatementForSelect = connection.prepareStatement(SELECT_APPOINTMENT_INFO);
            preparedStatementForSelect.setString(1,title);
            preparedStatementForSelect.setLong(2,type.getId());
            ResultSet resultSet = preparedStatementForSelect.executeQuery();
            if(resultSet.next()){
                appointmentInfo = new AppointmentInfo();
                appointmentInfo.setId(resultSet.getLong(1));
                appointmentInfo.setInfo(resultSet.getString(2));
                appointmentInfo.setType(resultSet.getInt(3));
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatementForInsert != null && !preparedStatementForInsert.isClosed()) {
                    preparedStatementForInsert.close();
                }
            } catch (SQLException throwables) {
                throw new DAOException("Close preparedStatement error ", throwables);
            }
            try {
                if (preparedStatementForSelect != null && !preparedStatementForSelect.isClosed()) {
                    preparedStatementForSelect.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return appointmentInfo;
    }


}
