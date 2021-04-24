package com.hospital.dao.impl;

import com.hospital.bean.Staff;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.StaffDAO;
import com.hospital.dao.connection.ConnectionPoolException;
import com.hospital.dao.connection.PoolProvider;
import com.hospital.dao.exception.DAOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StaffDAOTest {

    private static final String RESOURCE_FILE = "db_test";


    private static final StaffDAO staffDAO = DAOProvider.getInstance().getStaffDAO();


    @BeforeClass
    public static void connectionPoolInit() throws ConnectionPoolException {
        PoolProvider.getConnectionPool().init(RESOURCE_FILE);
    }

    @AfterClass
    public static void connectionPoolDispose() throws ConnectionPoolException {
        PoolProvider.getConnectionPool().dispose();
    }

    @Test
    public void getStaffPositive() throws DAOException {
       assertNotNull(staffDAO.getStaffById(1L));
    }

    @Test
    public void getStaffNegative() throws DAOException {
        assertNull(staffDAO.getStaffById(5L));
    }

    @Test
    public void getStaffAccountPositive() throws DAOException {
        assertNotNull(staffDAO.getStaffByAccount(48L));
    }

    @Test
    public void update() throws DAOException {
        Staff staff = new Staff();
        staff.setId(1L);
        staff.setDepartment(1);
        staff.setFirstname("staff1");
        staff.setLastname("LstStaff");
        staffDAO.update(staff);
        assertNotEquals(staffDAO.getStaffById(1L),staff);
    }

}
