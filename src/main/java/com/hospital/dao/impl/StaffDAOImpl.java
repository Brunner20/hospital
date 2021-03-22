package com.hospital.dao.impl;

import com.hospital.dao.DAOException;
import com.hospital.dao.StaffDAO;
import com.hospital.dao.connection.ConnectionPool;
import com.hospital.dao.connection.ConnectionPoolException;
import com.hospital.dao.connection.PoolProvider;
import com.hospital.entity.Patient;
import com.hospital.entity.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements StaffDAO {

    private static final String UPDATE_STAFF ="UPDATE hospital.staff SET firstname= ?, lastname = ?,  " +
            "staff_pic = ?, department_id = ?, type_id = ? WHERE id = ?";

    private static final String SELECT_PATIENTS = "SELECT * FROM hospital.patients WHERE attending_doctor =?";
    private static final String SELECT_STAFF_BY_ID = "SELECT * FROM hospital.staff WHERE id =?";

    private final ConnectionPool connectionPool = PoolProvider.getConnectionPool();

    @Override
    public void update(Staff staff) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_STAFF);
            preparedStatement.setString(1, staff.getFirstname());
            preparedStatement.setString(2,staff.getLastname());
            preparedStatement.setString(3,staff.getPicture());
            preparedStatement.setLong(4,staff.getDepartment().getId());
            preparedStatement.setLong(5,staff.getStaffTypeID());
            preparedStatement.setLong(6,staff.getId());
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
    public List<Patient> getAllPatientsByStaff(long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Patient> patients = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_PATIENTS);
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet set = preparedStatement.executeQuery();

            while (set.next()) {
                Patient patient = new Patient();
                patient.setId(set.getLong(1));
                patient.setFirstname(set.getString(2));
                patient.setLastname(set.getString(3));
                patient.setAge(set.getInt(4));
                if(set.getDate(5)!=null)
                patient.setReceiptDate(new Date(set.getDate(5).getTime()).toLocalDate());
                patient.setDepartment(set.getInt(6));
                patient.setAttendingDoctorID(set.getInt(7));
                patient.setStatusID(set.getInt(8));
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
    public Staff getStaffById(Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Staff staff = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                staff = new Staff();
                staff.setId(resultSet.getLong(1));
                staff.setFirstname(resultSet.getString(2));
                staff.setLastname(resultSet.getString(3));
                staff.setPicture(resultSet.getString(4));
                staff.setStaffTypeID(resultSet.getLong(5));
                staff.setDepartment(resultSet.getInt(6));
                staff.setAccountID(resultSet.getLong(7));

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
        return staff;
    }
}
