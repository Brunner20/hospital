package com.hospital.dao.impl;

import com.hospital.dao.DAOException;
import com.hospital.dao.PatientDAO;
import com.hospital.dao.connection.ConnectionPool;
import com.hospital.dao.connection.ConnectionPoolException;
import com.hospital.dao.connection.PoolProvider;
import com.hospital.entity.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    private static final String UPDATE_PATIENT ="UPDATE hospital.patients SET firstname= ?, lastname = ?, age = ?, " +
            " department_id = ?, attending_doctor = ?, status =?, account_id = ?, patient_pic = ? WHERE id = ?";

    private static final String UPDATE_AGE = "UPDATE hospital.patients SET age = ? where id = ?";
    private static final String UPDATE_DOCTOR= "UPDATE hospital.patients SET attending_doctor = ? and status = 1 where id = ?";
    private static final String SELECT_PATIENTS = "SELECT * FROM hospital.patients WHERE attending_doctor =?";
    private static final String GET_FREE_PATIENTS ="select * from hospital.patients where attending_doctor is null";
    private static final String GET_ALL_PATIENTS ="select * from hospital.patients where age is not null";
    private static final String SELECT_PATIENT_BY_ID = "SELECT * FROM hospital.patients WHERE id =?";

    private final ConnectionPool connectionPool = PoolProvider.getConnectionPool();

    @Override
    public void update(Patient patient) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_PATIENT);
            preparedStatement.setString(1,patient.getFirstname());
            preparedStatement.setString(2,patient.getLastname());
            preparedStatement.setInt(3,patient.getAge());
            preparedStatement.setLong(4,patient.getDepartment().getId());
            if(patient.getAttendingDoctorID()==0)
                preparedStatement.setString(5,null);
            else
                preparedStatement.setLong(5,patient.getAttendingDoctorID());
            preparedStatement.setLong(6,patient.getStatusID());
            preparedStatement.setLong(7,patient.getAccountID());
            preparedStatement.setString(8,patient.getPatientPic());
            preparedStatement.setLong(9,patient.getId());

            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
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
    public void updateAge(long id,int age) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_AGE);
            preparedStatement.setInt(1,age);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
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
    public List<Patient> getFreePatients() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Patient> patients = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(GET_FREE_PATIENTS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Patient patient = patientMapping(resultSet);
                patients.add(patient);
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
        return patients;
    }

    @Override
    public List<Patient> getAllPatientsByStaff(long attendingDoctorId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Patient> patients = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_PATIENTS);
            preparedStatement.setString(1, String.valueOf(attendingDoctorId));
            ResultSet set = preparedStatement.executeQuery();

            while (set.next()) {
                Patient patient = patientMapping(set);
                patients.add(patient);
            }

        } catch (ConnectionPoolException | SQLException e) {
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
        return patients;
    }

    @Override
    public void updateDoctor(List<String> selectedPatientsIds, Long doctorId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_DOCTOR);
            for(String patientsId: selectedPatientsIds) {
                preparedStatement.setLong(1, doctorId);
                preparedStatement.setLong(2, Integer.parseInt(patientsId));
                preparedStatement.executeUpdate();
            }

        } catch (ConnectionPoolException | SQLException e) {
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
    public Patient getPatientById(Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Patient patient = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                patient = patientMapping(resultSet);
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
        return patient;
    }

    @Override
    public List<Patient> getAll() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Patient> patients = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_PATIENTS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Patient patient = patientMapping(resultSet);
                patients.add(patient);
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
        return patients;
    }

    private Patient patientMapping(ResultSet resultSet) throws SQLException {
        Patient patient = new Patient();
        patient.setId(resultSet.getLong(1));
        patient.setFirstname(resultSet.getString(2));
        patient.setLastname(resultSet.getString(3));
        patient.setAge(resultSet.getInt(4));
        patient.setPatientPic(resultSet.getString(5));
        patient.setDepartment(resultSet.getInt(6));
        patient.setAttendingDoctorID(resultSet.getLong(7));
        patient.setStatusID(resultSet.getInt(8));
        patient.setAccountID(resultSet.getInt(9));
        return patient;
    }
}
