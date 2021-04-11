package com.hospital.dao.impl;

import com.hospital.bean.Epicrisis;
import com.hospital.dao.EpicrisisDAO;
import com.hospital.dao.connection.ConnectionPool;
import com.hospital.dao.connection.ConnectionPoolException;
import com.hospital.dao.connection.PoolProvider;
import com.hospital.dao.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for working with the database for the epicrisis
 */
public class EpicrisisDAOImpl implements EpicrisisDAO {

   private static final Logger logger = LogManager.getLogger(EpicrisisDAOImpl.class);

    private static final String INSERT_EPICRISIS = "insert into hospital.epicrisis(definitive_diagnosis,date_of_receip,date_of_issue,patient,preliminary_diagnosis,history_id) values(?,?,?,?,?,?)";
    private static final String UPDATE_EPICRISIS = "update hospital.epicrisis set definitive_diagnosis = ?, date_of_issue = ?, history_id = ? where id = ?";
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
            preparedStatement.setDate(2,epicrisis.getReceiptDate());
            preparedStatement.setDate(3,epicrisis.getDischargeDate());
            preparedStatement.setLong(4,epicrisis.getPatientId());
            preparedStatement.setString(5,epicrisis.getPreliminaryDiagnosis());
            if(epicrisis.getMedicalHistoryId()==0)
            { preparedStatement.setString(6,null);}
            else
            { preparedStatement.setLong(6,epicrisis.getMedicalHistoryId());}
            preparedStatement.execute();

        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException("insert error ",e);
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
    }

    @Override
    public List<Epicrisis> getEpicrisisByPatientId(long patientId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Epicrisis> epicrisisList = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_PATIENT);
            preparedStatement.setLong(1,patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Epicrisis epicrisis = mappingEpicrisis(resultSet);
                epicrisisList.add(epicrisis);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException("select error ",e);
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
        return epicrisisList;
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
            if(epicrisis.getMedicalHistoryId() == 0)
                preparedStatement.setString(3,null);
            else
                preparedStatement.setLong(3,epicrisis.getMedicalHistoryId());
            preparedStatement.setLong(4,epicrisis.getId());
            preparedStatement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException("update error ",e);
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
    }

    private Epicrisis mappingEpicrisis(ResultSet resultSet) throws SQLException {
        Epicrisis epicrisis = new Epicrisis();
        epicrisis.setId(resultSet.getInt(1));
        epicrisis.setPreliminaryDiagnosis(resultSet.getString(2));
        epicrisis.setDefinitiveDiagnosis(resultSet.getString(3));
        epicrisis.setReceiptDate(resultSet.getDate(4));
        epicrisis.setDischargeDate(resultSet.getDate(5));
        epicrisis.setPatientId(resultSet.getLong(6));
        return epicrisis;

    }


}
