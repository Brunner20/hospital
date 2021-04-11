package com.hospital.service;

import com.hospital.bean.Patient;
import com.hospital.service.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;

/**
 * The interface Patient service.
 */
public interface PatientService {

    /**
     * Find Patient by id
     * @param id the patient id
     * @return found Patient
     * @throws ServiceException if a service exception occurred while processing
     */
    Patient getPatientById(Long id) throws ServiceException;

    /**
     * Find Patient by account id
     * @param id  the account of patient id
     * @return found patient
     * @throws ServiceException if a service exception occurred while processing
     */
    Patient getPatientByAccount(long id) throws ServiceException;

    /**
     * Find all patients
     * @return list of all patients
     * @throws ServiceException if a service exception occurred while processing
     */
    List<Patient> getAll() throws ServiceException;

    /**
     * Find patients without attending doctor
     * @return  list of patients without a attending doctor
     * @throws ServiceException if a service exception occurred while processing
     */
    List<Patient> getFreePatients() throws ServiceException;

    /**
     * Find patient by his attending doctor id
     * @param id  attending doctor id
     * @return  list of patients with a given attending doctor
     * @throws ServiceException if a service exception occurred while processing
     */
    List<Patient> getAllPatientsByStaff(long id) throws ServiceException;

    /**
     * Update information about patient
     * @param patient patient with new data to be saved
     * @throws ServiceException if a service exception occurred while processing
     */
    void update(Patient patient) throws ServiceException;


    /**
     * Save picture of patient
     * @param patient staff who needs to save the picture
     * @param part picture data
     * @throws ServiceException if a service exception occurred while processing
     */
    void savePictureToPatient(Patient patient, Part part) throws ServiceException;
}
