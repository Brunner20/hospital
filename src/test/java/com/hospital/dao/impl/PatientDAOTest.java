package com.hospital.dao.impl;

import com.hospital.bean.Patient;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.PatientDAO;
import com.hospital.dao.connection.ConnectionPoolException;
import com.hospital.dao.connection.PoolProvider;
import com.hospital.dao.exception.DAOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PatientDAOTest {

    private static final String RESOURCE_FILE = "db_test";


    private static final PatientDAO patientDAO = DAOProvider.getInstance().getPatientDAO();


    @BeforeClass
    public static void connectionPoolInit() throws ConnectionPoolException {
        PoolProvider.getConnectionPool().init(RESOURCE_FILE);
    }

    @AfterClass
    public static void connectionPoolDispose() throws ConnectionPoolException {
        PoolProvider.getConnectionPool().dispose();
    }


    @Test
    public void getFreeTest() throws DAOException {
        assertEquals(patientDAO.getFreePatients(), Collections.emptyList());
    }

    @Test
    public void getFreeNegativeTest() throws DAOException {
       List<Patient> patientList = patientDAO.getAll();
       for (Patient patient :patientList){
           patient.setStatusID(3);
           patientDAO.update(patient);
       }
       assertNotEquals(patientDAO.getFreePatients(), Collections.emptyList());
    }

    @Test
    public void update() throws DAOException {
        Patient patient = patientDAO.getPatientById(1L);
        patient.setId(1);
        patient.setFirstname("alexand");
        patientDAO.update(patient);
        assertEquals(patientDAO.getPatientById(1L),patient);
    }

}
