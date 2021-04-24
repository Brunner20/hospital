package com.hospital.dao.impl;


import com.hospital.bean.UserInfo;
import com.hospital.dao.AccountDAO;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.connection.ConnectionPoolException;
import com.hospital.dao.connection.PoolProvider;
import com.hospital.dao.exception.DAOException;
import com.hospital.dao.exception.DataNotFoundException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AccountDAOTest {

    private static final String RESOURCE_FILE = "db_test";


    private static final AccountDAO accountDao = DAOProvider.getInstance().getAccountDAO();


    @BeforeClass
    public static void connectionPoolInit() throws ConnectionPoolException {
        PoolProvider.getConnectionPool().init(RESOURCE_FILE);
    }

    @AfterClass
    public static void connectionPoolDispose() throws ConnectionPoolException {
        PoolProvider.getConnectionPool().dispose();
    }


    @Test
    public void regPatient() throws DAOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstname("Антон");
        userInfo.setLastname("Бруннер");
        userInfo.setLogin("anton");
        userInfo.setPassword("Anton23");
        userInfo.setRoleId(3);
        accountDao.registration(userInfo);
    }

    @Test
    public void regStaff() throws DAOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstname("Иван");
        userInfo.setLastname("Иванов");
        userInfo.setLogin("ivan");
        userInfo.setPassword("Ivan21");
        userInfo.setRoleId(2);
        accountDao.registration(userInfo);
    }

    @Test
    public void regAdmin() throws DAOException{
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstname("Виталий");
        userInfo.setLastname("Кушин");
        userInfo.setLogin("vital");
        userInfo.setPassword("Vital23");
        userInfo.setRoleId(1);
        accountDao.registration(userInfo);
    }

    @Test
    public void authorizationTest1() throws DAOException {
        assertNotNull(accountDao.authorization("anton","Anton23"));
    }

    @Test
    public void authorizationTest2() throws DAOException {
        assertNotNull(accountDao.authorization("ivan","Ivan21"));
    }

    @Test
    public void authorizationNegativeTest1() throws DAOException{
        assertNull(accountDao.authorization("vital","Vital26"));
    }

    @Test
    public void authorizationNegativeTest12() throws DAOException{
        assertNull(accountDao.authorization("anton","anton3"));
    }

    @Test
    public void findByLoginPositiveTest() throws DAOException {
        assertNotNull(accountDao.findByLogin("anton"));
    }

    @Test
    public void findByLoginNegativeTest() throws DAOException {
        assertNull(accountDao.findByLogin("alex"));
    }

    @Test
    public void updatePasswordPositiveTest() throws DAOException {
        Long id = accountDao.findByLogin("vital");
        accountDao.updatePassword(id,"Vital23","viTal00");
    }

    @Test(expected = DataNotFoundException.class)
    public void updatePasswordNegativeTest() throws DAOException {
        Long id = accountDao.findByLogin("vital");
        accountDao.updatePassword(id,"sdasa","viTal00");
    }
}