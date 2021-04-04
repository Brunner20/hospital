package com.hospital.dao.impl;

import com.hospital.dao.StaffDAO;
import com.hospital.dao.connection.ConnectionPool;
import com.hospital.dao.connection.ConnectionPoolException;
import com.hospital.dao.connection.PoolProvider;
import com.hospital.dao.exception.DAOException;
import com.hospital.entity.Staff;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements StaffDAO {

    private static final Logger logger = LogManager.getLogger(StaffDAOImpl.class);

    private static final String UPDATE_STAFF ="UPDATE hospital.staff SET firstname= ?, lastname = ?,  " +
            "staff_pic = ?, department_id = ?, type_id = ? WHERE id = ?";

    private static final String SELECT_STAFF_BY_ID = "SELECT * FROM hospital.staff WHERE id =?";
    private static final String GET_ALL_STAFF ="select * from hospital.staff ";


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
            logger.log(Level.ERROR,e);
            throw new DAOException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            }catch (SQLException e){
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }


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
                staff = staffMapping(resultSet);
            }
        } catch (ConnectionPoolException | SQLException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            }catch (SQLException e){
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return staff;
    }



    @Override
    public List<Staff> getAll() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Staff> allStaff = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_STAFF);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Staff staff = staffMapping(resultSet);
                allStaff.add(staff);
            }

        } catch (ConnectionPoolException | SQLException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            }catch (SQLException e){
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return allStaff;
    }

    private Staff staffMapping(ResultSet resultSet) throws SQLException {
        Staff staff = new Staff() ;
        staff.setId(resultSet.getLong(1));
        staff.setFirstname(resultSet.getString(2));
        staff.setLastname(resultSet.getString(3));
        staff.setPicture(resultSet.getString(4));
        staff.setStaffTypeID(resultSet.getLong(5));
        staff.setDepartment(resultSet.getInt(6));
        staff.setAccountID(resultSet.getLong(7));
        return staff;
    }
}
