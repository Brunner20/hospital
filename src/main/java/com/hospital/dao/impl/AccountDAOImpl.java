package com.hospital.dao.impl;

import com.hospital.connection.ConnectionPool;
import com.hospital.connection.PoolProvider;
import com.hospital.dao.AccountDAO;
import com.hospital.dao.DAOException;
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


    private ConnectionPool connectionPool = PoolProvider.getConnectionPool();


    @Override
    public Visitor authorization(String login, String password) throws DAOException {

        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
        } catch (SQLException throwables) {
            throw new DAOException(throwables);
        }
        return getAccount(preparedStatement);
    }

    @Override
    public boolean registration(RegistrationInfo regInfo) throws DAOException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isInserted = false;

        try {
            preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);
            preparedStatement.setString(1, regInfo.getLogin());
            preparedStatement.setString(2, regInfo.getPassword());
            preparedStatement.setInt(3,2);

            preparedStatement.execute();

            PreparedStatement accountSt = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD);
            accountSt.setString(1, regInfo.getLogin());
            accountSt.setString(2, regInfo.getPassword());
            ResultSet resultSet = accountSt.executeQuery();
            if(resultSet.next()){
               isInserted = insertPatient(regInfo.getName(),regInfo.getSurname(),resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throw new DAOException(throwables);
        }

        return isInserted;
    }

    private boolean insertPatient(String firstname,String lastname, int accountId) throws SQLException {
        Connection connection = connectionPool.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT);
        preparedStatement.setString(1,firstname);
        preparedStatement.setString(2,lastname);
        preparedStatement.setInt(3,accountId);

        return preparedStatement.execute();
    }


    private Visitor getAccount(PreparedStatement preparedStatement) throws DAOException {
        Visitor visitor = null;
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("title").equals("patient")) {
                    visitor = getPatientInfo(resultSet.getString(5));
                } else {
                    visitor = getStaffInfo(resultSet.getString(5));
                }
            }

        } catch (SQLException throwables) {
            throw new DAOException(throwables);
        }
        return visitor;
    }

    private Visitor getStaffInfo(String accountId) throws SQLException {

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
        return staff;
    }

    private Patient getPatientInfo(String accountId) throws SQLException {

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
            patient.setReceiptDate(new Date(resultSet.getDate(5).getTime()).toLocalDate());
            patient.setDepartmentID(resultSet.getInt(6));
            patient.setAttendingDoctorID(resultSet.getInt(7));
            patient.setStatusID(resultSet.getInt(8));
            patient.setAccountID(resultSet.getInt(9));
        }
        return patient;
    }
}
