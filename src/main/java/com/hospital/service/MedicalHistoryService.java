package com.hospital.service;

import com.hospital.bean.MedicalHistory;
import com.hospital.service.exception.ServiceException;

/**
 * The interface medical history service.
 */
public interface MedicalHistoryService {

    /**
     * Add new Medical History
     * @param medicalHistory medicalHistory to save
     * @throws ServiceException if a service exception occurred while processing
     */
    void add(MedicalHistory medicalHistory) throws ServiceException;

    /**
     * Find medical history by owner
     * @param patientId patient's id who owns MedicalHistory
     * @return found MedicalHistory
     * @throws ServiceException if a service exception occurred while processing
     */
    MedicalHistory getByPatientId(long patientId) throws ServiceException;

    /**
     * Update information about medicalHistory
     * @param medicalHistory medicalHistory with new data to be saved
     * @throws ServiceException if a service exception occurred while processing
     */
    void  update(MedicalHistory medicalHistory) throws ServiceException;
}
