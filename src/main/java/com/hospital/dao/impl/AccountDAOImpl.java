package com.hospital.dao.impl;

import com.hospital.dao.AccountDAO;
import com.hospital.dao.connection.ConnectionPool;
import com.hospital.dao.connection.ConnectionPoolException;
import com.hospital.dao.connection.PoolProvider;
import com.hospital.dao.exception.DAOException;
import com.hospital.dao.exception.DataNotFoundException;
import com.hospital.entity.Patient;
import com.hospital.entity.Staff;
import com.hospital.entity.UserInfo;
import com.hospital.entity.Visitor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class AccountDAOImpl implements AccountDAO {

    private static final Logger logger = LogManager.getLogger(AccountDAOImpl.class);

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM hospital.accounts JOIN hospital.roles ON" +
            " hospital.accounts.id_role = hospital.roles.id WHERE login = ? and password = ? ";

    private static final String FIND_PATIENT_BY_ACCOUNT = "select * from hospital.patients WHERE hospital.patients.account_id = ?";
    private static final String FIND_STAFF_BY_ACCOUNT = "select * from hospital.staff WHERE hospital.staff.account = ?";
    private static final String FIND_USER_BY_LOGIN = "select * from hospital.accounts where login = ?";
    private static final String FIND_ACCOUNT_BY_ID_AND_PASSWORD = "SELECT * FROM hospital.accounts where id=? and password= ?";
    private static final String UPDATE_PASSWORD = "update hospital.accounts set password = ? where id = ?";
    private static final String INSERT_ACCOUNT = "insert into hospital.accounts(login,password,id_role) VALUES (?,?,?)";
    private static final String INSERT_PATIENT = "insert into hospital.patients(account_id,status) VALUES (?,2)";
    private static final String INSERT_STAFF = "insert into hospital.staff(firstname,lastname,account) VALUES (?,?,?)";

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
            logger.log(Level.ERROR,e);
            throw new DAOException("find error ",e);
        }finally {
            connectionPool.releaseConnection(connection);
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR,e);
                    throw new DAOException("Close preparedStatement error ", e);
                }
            }
        }
        return visitor;
    }

    @Override
    public void registration(UserInfo regInfo) throws DAOException {
        Connection connection = null;
        PreparedStatement accountSt = null;
        Savepoint savepointOne = null;

        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            savepointOne = connection.setSavepoint("SavepointOne");

            insertAccount(regInfo,connection);
            accountSt = connection.prepareStatement(FIND_USER_BY_LOGIN);
            accountSt.setString(1, regInfo.getLogin());
            ResultSet resultSet = accountSt.executeQuery();
            if(resultSet.next()) {
                if (regInfo.getRoleId() == 3) {
                    insertPatient(resultSet.getInt(1), connection);
                } else {
                    insertStaff(regInfo, resultSet.getInt(1), connection);
                }
                connection.commit();
            }
            accountSt.close();

        } catch (SQLException | ConnectionPoolException throwables) {
            logger.log(Level.ERROR,throwables);
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
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
    }

    @Override
    public void updatePassword(long accountId, String oldPass, String newPass) throws DAOException {
        Connection connection = null;
        Connection connectionForUpdate;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementForUpdate = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ACCOUNT_BY_ID_AND_PASSWORD);
            preparedStatement.setLong(1, accountId);
            preparedStatement.setString(2, oldPass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                connectionForUpdate = connectionPool.getConnection();
                preparedStatementForUpdate = connectionForUpdate.prepareStatement(UPDATE_PASSWORD);
                preparedStatementForUpdate.setString(1, newPass);
                preparedStatementForUpdate.setLong(2, accountId);
                preparedStatementForUpdate.execute();
            }
            else {
                logger.log(Level.WARN,"wrong old password");
                throw new DataNotFoundException("wrong old password");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.WARN,e);
            throw new DAOException("find error ",e);
        }finally {
            connectionPool.releaseConnection(connection);
            connectionPool.releaseConnection(connection);
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.log(Level.WARN,e);
                    throw new DAOException("Close preparedStatement error ", e);
                }
            }
            if(preparedStatementForUpdate!=null){
                try {
                    preparedStatementForUpdate.close();
                } catch (SQLException e) {
                    logger.log(Level.WARN,e);
                    throw new DAOException("Close preparedStatement error ", e);
                }
            }
        }
    }

    @Override
    public Long findByLogin(String login) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement;
        Long id = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException | ConnectionPoolException throwables) {
            throw new DAOException(throwables);
        }

        return id;
    }

    private void insertStaff(UserInfo info, int accountId, Connection connection)throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STAFF);
        preparedStatement.setString(1,info.getFirstname());
        preparedStatement.setString(2,info.getLastname());
        preparedStatement.setInt(3,accountId);
        preparedStatement.execute();
        preparedStatement.close();

    }

    private void insertPatient( int accountId,Connection connection) throws SQLException, ConnectionPoolException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT);
        preparedStatement.setInt(1,accountId);
        preparedStatement.execute();
        preparedStatement.close();
    }

    private void insertAccount(UserInfo userInfo, Connection connection) throws SQLException, ConnectionPoolException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);
        preparedStatement.setString(1, userInfo.getLogin());
        preparedStatement.setString(2, userInfo.getPassword());
        preparedStatement.setLong(3, userInfo.getRoleId());
        preparedStatement.execute();
        preparedStatement.close();
    }


    private Visitor getAccount(PreparedStatement preparedStatement) throws SQLException, ConnectionPoolException, DAOException {
        Visitor visitor = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getString("title").equals("patient")) {
                visitor = getPatientInfo(resultSet.getString(1));
            } else if(resultSet.getString("title").equals("staff")) {
                visitor = getStaffInfo(resultSet.getString(1));
            }
        }
        preparedStatement.close();
        return visitor;
    }

    private Visitor getStaffInfo(String accountId) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Staff staff =null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_STAFF_BY_ACCOUNT);
            preparedStatement.setString(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                staff = new Staff();
                staff.setId(resultSet.getLong(1));
                staff.setFirstname(resultSet.getString(2));
                staff.setLastname(resultSet.getString(3));
                staff.setPicture(resultSet.getString(4));
                staff.setStaffTypeID(resultSet.getInt(5));
                staff.setDepartment(resultSet.getInt(6));
                staff.setAccountID(resultSet.getInt(7));

            }
        } catch (ConnectionPoolException | SQLException e) {
            logger.log(Level.WARN,e);
            throw new DAOException(e);
        } finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throw new DAOException(throwables);
            }
        }
        return staff;
    }

    private Patient getPatientInfo(String accountId) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Patient patient = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_PATIENT_BY_ACCOUNT);
            preparedStatement.setString(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                patient = new Patient();
                patient.setId(resultSet.getLong(1));
                patient.setFirstname(resultSet.getString(2));
                patient.setLastname(resultSet.getString(3));
                patient.setAge(resultSet.getInt(4));
                patient.setPatientPic(resultSet.getString(5));
                patient.setDepartment(resultSet.getInt(6));
                patient.setAttendingDoctorID(resultSet.getLong(7));
                patient.setStatusID(resultSet.getInt(8));
                patient.setAccountID(resultSet.getInt(9));
            }
        } catch (SQLException | ConnectionPoolException throwables) {
            logger.log(Level.WARN,throwables);
            throw new DAOException(throwables);
        }finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                logger.log(Level.WARN,throwables);
                throw new DAOException(throwables);
            }
        }
        return patient;
    }
}
