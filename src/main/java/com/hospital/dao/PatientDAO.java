package com.hospital.dao;

import com.hospital.bean.Patient;
import com.hospital.dao.exception.DAOException;

import java.util.List;

/**
 *The interface Patient dao.
 */
public interface PatientDAO {

    /**
     * Find Patient by id
     * @param id the patient id
     * @return found Patient
     * @throws DAOException  if an dao exception occurred while processing
     */
    Patient getPatientById(Long id)throws DAOException;

    /**
     * Find Patient by account id
     * @param accountId  the account of patient id
     * @return found patient
     * @throws DAOException if an dao exception occurred while processing
     */
    Patient getPatientByAccount(long accountId) throws DAOException;

    /**
     * Find all patients
     * @return list of all patients
     * @throws DAOException if an dao exception occurred while processing
     */
    List<Patient> getAll() throws DAOException;

    /**
     * Find patient by his attending doctor id
     * @param id  attending doctor id
     * @return  list of patients with a given attending doctor
     * @throws DAOException if an dao exception occurred while processing
     */
    List<Patient> getAllPatientsByStaff(long id) throws DAOException;

    /**
     * Find patients without attending doctor
     * @return  list of patients without a attending doctor
     * @throws DAOException if an dao exception occurred while processing
     */
    List<Patient> getFreePatients() throws DAOException;

    /**
     * Update information about patient
     * @param patient patient with new data to be saved
     * @throws DAOException if an dao exception occurred while processing
     */
    void update(Patient patient) throws DAOException;

}
