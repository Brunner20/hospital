package com.hospital.dao;

import com.hospital.bean.Patient;
import com.hospital.dao.exception.DAOException;

import java.util.List;

public interface PatientDAO {

    Patient getPatientById(Long id)throws DAOException;

    Patient getPatientByAccount(long accountId) throws DAOException;

    List<Patient> getAll() throws DAOException;

    List<Patient> getAllPatientsByStaff(long id) throws DAOException;

    List<Patient> getFreePatients() throws DAOException;

    void update(Patient patient) throws DAOException;

}
