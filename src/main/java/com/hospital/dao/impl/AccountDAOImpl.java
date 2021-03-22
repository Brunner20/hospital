package com.hospital.dao.impl;

import com.hospital.dao.AccountDAO;
import com.hospital.dao.DAOException;
import com.hospital.dao.connection.ConnectionPool;
import com.hospital.dao.connection.ConnectionPoolException;
import com.hospital.dao.connection.PoolProvider;
import com.hospital.entity.Patient;
import com.hospital.entity.RegistrationInfo;
import com.hospital.entity.Staff;
import com.hospital.entity.Visitor;

import java.sql.*;

public class AccountDAOImpl implements AccountDAO {

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM hospital.accounts JOIN hospital.roles ON" +
            " hospital.accounts.id_role = hospital.roles.id WHERE login = ? AND password = ?";

    private static final String FIND_PATIENT_BY_ACCOUNT = "select * from hospital.patients" +
            " WHERE hospital.patients.account_id = ?";

    private static final String FIND_STAFF_BY_ACCOUNT = "select * from hospital.staff" +
            " WHERE hospital.staff.account = ?";

    private static final String INSERT_ACCOUNT = "insert into hospital.accounts(login,password,id_role) VALUES (?,?,?)";
    private static final String INSERT_PATIENT = "insert into hospital.patients(firstname,lastname,account_id) VALUES (?,?,?)";


    private final ConnectionPool connectionPool = PoolProvider.getConnectionPool();


    @Override
    public Visitor authorization(String login, String password) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Visitor visitor;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            visitor = getAccount(preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("find error ",e);
        }finally {
            connectionPool.releaseConnection(connection);
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DAOException("Close preparedStatement error ", e);
                }
            }
        }
        return visitor;
    }

    @Override
    public boolean registration(RegistrationInfo regInfo) throws DAOException {
        Connection connection = null;
        PreparedStatement accountSt = null;
        boolean isInserted = false;
        Savepoint savepointOne = null;

        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            savepointOne = connection.setSavepoint("SavepointOne");
            if(!insertAccount(regInfo.getLogin(),regInfo.getPassword(),connection))
            {
                accountSt = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD);
                accountSt.setString(1, regInfo.getLogin());
                accountSt.setString(2, regInfo.getPassword());
                ResultSet resultSet = accountSt.executeQuery();
                if(resultSet.next()){
                   isInserted = insertPatient(regInfo.getFirstname(),regInfo.getLastname(),
                           resultSet.getInt(1),connection);
                   connection.commit();
                }
                accountSt.close();
            }

        } catch (SQLException | ConnectionPoolException throwables) {
            try {
                if(connection!=null)
                connection.rollback(savepointOne);
            } catch (SQLException e) {
                throw new DAOException(throwables);
            }
            throw new DAOException(throwables);
        }finally {
            connectionPool.releaseConnection(connection);
            try {
                if (accountSt != null && !accountSt.isClosed()) {
                    accountSt.close();
                }
            }catch (SQLException e){
                throw new DAOException("Close preparedStatement error ", e);
            }
        }

        return isInserted;
    }

    private boolean insertPatient(String firstname,String lastname, int accountId,Connection connection) throws SQLException, ConnectionPoolException {
        boolean isInserted;
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT);
        preparedStatement.setString(1,firstname);
        preparedStatement.setString(2,lastname);
        preparedStatement.setInt(3,accountId);
        isInserted= preparedStatement.execute();
        preparedStatement.close();
        return isInserted;
    }

    private boolean insertAccount(String login,String password,Connection connection) throws SQLException, ConnectionPoolException {
        boolean isInserted;
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.setInt(3,3);
        isInserted= preparedStatement.execute();
        preparedStatement.close();

        return isInserted;
    }


    private Visitor getAccount(PreparedStatement preparedStatement) throws SQLException, ConnectionPoolException {
        Visitor visitor = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getString("title").equals("patient")) {
                visitor = getPatientInfo(resultSet.getString(1));
            } else {
                visitor = getStaffInfo(resultSet.getString(1));
            }
        }
        preparedStatement.close();
        return visitor;
    }

    private Visitor getStaffInfo(String accountId) throws SQLException, ConnectionPoolException {

        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_STAFF_BY_ACCOUNT);
        preparedStatement.setString(1, accountId);
        ResultSet resultSet = preparedStatement.executeQuery();


        Staff staff = new Staff();
        if (resultSet.next()) {
            staff.setId(resultSet.getLong(1));
            staff.setFirstname(resultSet.getString(2));
            staff.setLastname(resultSet.getString(3));
            staff.setPicture(resultSet.getString(4));
            staff.setStaffTypeID(resultSet.getInt(5));
            staff.setDepartmentID(resultSet.getInt(6));
            staff.setAccountID(resultSet.getInt(7));

        }
        connectionPool.releaseConnection(connection);
        preparedStatement.close();
        return staff;
    }

    private Patient getPatientInfo(String accountId) throws SQLException, ConnectionPoolException {

        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_PATIENT_BY_ACCOUNT);
        preparedStatement.setString(1, accountId);
        ResultSet resultSet = preparedStatement.executeQuery();

        Patient patient = new Patient();
        if (resultSet.next()) {
            patient.setId(resultSet.getLong(1));
            patient.setFirstname(resultSet.getString(2));
            patient.setLastname(resultSet.getString(3));
            patient.setAge(resultSet.getInt(4));
            if(resultSet.getDate(5)!=null)
            patient.setReceiptDate(new Date(resultSet.getDate(5).getTime()).toLocalDate());
            patient.setDepartmentID(resultSet.getInt(6));
            patient.setAttendingDoctorID(resultSet.getInt(7));
            patient.setStatusID(resultSet.getInt(8));
            patient.setAccountID(resultSet.getInt(9));
        }
        connectionPool.releaseConnection(connection);
        preparedStatement.close();
        return patient;
    }
}
