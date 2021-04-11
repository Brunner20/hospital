package com.hospital.dao;

import com.hospital.bean.MedicalHistory;
import com.hospital.dao.exception.DAOException;

/**
 * The interface Medical history dao.
 */
public interface MedicalHistoryDAO {

    /**
     * Add new Medical History
     * @param medicalHistory medicalHistory to save
     * @throws DAOException if an dao exception occurred while processing
     */
    void add(MedicalHistory medicalHistory) throws DAOException;

    /**
     * Find medical history by owner
     * @param patientId patient's id who owns MedicalHistory
     * @return found MedicalHistory
     * @throws DAOException if an dao exception occurred while processing
     */
    MedicalHistory getByPatientId(long patientId) throws DAOException;

    /**
     * Update information about medicalHistory
     * @param medicalHistory medicalHistory with new data to be saved
     * @throws DAOException if an dao exception occurred while processing
     */
    void  update(MedicalHistory medicalHistory) throws DAOException;
}
