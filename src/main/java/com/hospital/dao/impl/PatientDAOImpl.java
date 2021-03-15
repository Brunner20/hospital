package com.hospital.dao.impl;

import com.hospital.dao.DAOException;
import com.hospital.dao.PatientDAO;
import com.hospital.dao.connection.ConnectionPool;
import com.hospital.dao.connection.ConnectionPoolException;
import com.hospital.dao.connection.PoolProvider;
import com.hospital.entity.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientDAOImpl implements PatientDAO {

    private static final String UPDATE_PATIENT ="UPDATE hospital.patients SET firstname= ?, lastname = ?, age = ?, " +
            "receipt_date = ?, department_id = ?, attending_doctor = ?, status =?, account_id = ?  WHERE id = ?";

    private static final String UPDATE_AGE = "UPDATE hospital.patients SET age = ? where id = ?";

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
            preparedStatement.setDate(4,java.sql.Date.valueOf(patient.getReceiptDate()));
            preparedStatement.setLong(5,patient.getDepartmentID());
            preparedStatement.setLong(6,patient.getAttendingDoctorID());
            preparedStatement.setLong(7,patient.getStatusID());
            preparedStatement.setLong(8,patient.getAccountID());
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
}
