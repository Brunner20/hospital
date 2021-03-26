package com.hospital.dao.impl;

import com.hospital.dao.DAOException;
import com.hospital.dao.EpicrisisDAO;
import com.hospital.dao.connection.ConnectionPool;
import com.hospital.dao.connection.ConnectionPoolException;
import com.hospital.dao.connection.PoolProvider;
import com.hospital.entity.Epicrisis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EpicrisisDAOImpl implements EpicrisisDAO {


    private static final String INSERT_EPICRISIS = "insert into hospital.epicrisis(definitive_diagnosis,date_of_issue,patient,preliminary_diagnosis) values(?,?,?,?)";
    private static final String UPDATE_EPICRISIS = "update hospital.epicrisis set definitive_diagnosis = ?, date_of_issue = ? where id = ?";
    private static final String SELECT_BY_PATIENT = "select * from hospital.epicrisis where patient = ?";

    private final ConnectionPool connectionPool = PoolProvider.getConnectionPool();

    @Override
    public void addEpicrisis(Epicrisis epicrisis) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_EPICRISIS);

            preparedStatement.setString(1,epicrisis.getDefinitiveDiagnosis());
            preparedStatement.setDate(2,epicrisis.getDischargeDate());
            preparedStatement.setLong(3,epicrisis.getPatientId());
            preparedStatement.setString(4,epicrisis.getPreliminaryDiagnosis());
            preparedStatement.execute();

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
    }

    @Override
    public Epicrisis getEpicrisisByPatientId(long patientId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Epicrisis epicrisis = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_PATIENT);

            preparedStatement.setLong(1,patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                epicrisis = mappingEpicrisis(resultSet);
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("select error ",e);
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
        return epicrisis;
    }

    @Override
    public void update(Epicrisis epicrisis) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_EPICRISIS);

            preparedStatement.setString(1,epicrisis.getDefinitiveDiagnosis());
            preparedStatement.setDate(2,epicrisis.getDischargeDate());
            preparedStatement.setLong(3,epicrisis.getId());
            preparedStatement.execute();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("update error ",e);
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
    }

    private Epicrisis mappingEpicrisis(ResultSet resultSet) throws SQLException {
        Epicrisis epicrisis = new Epicrisis();
        epicrisis.setId(resultSet.getInt(1));
        epicrisis.setPreliminaryDiagnosis(resultSet.getString(2));
        epicrisis.setDefinitiveDiagnosis(resultSet.getString(3));
        epicrisis.setDischargeDate(resultSet.getDate(4));
        epicrisis.setPatientId(resultSet.getLong(5));
        return epicrisis;

    }


}
