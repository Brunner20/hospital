package com.hospital.service;

import com.hospital.bean.Epicrisis;
import com.hospital.bean.dto.EpicrisisDTO;
import com.hospital.service.exception.ServiceException;

import java.util.List;

/**
 * The interface epicrisis service.
 */
public interface EpicrisisService {

    /**
     * Add new epicrisis
     * @param epicrisis epicrisis to save
     * @throws ServiceException if a service exception occurred while processing
     */
    void addEpicrisis(Epicrisis epicrisis) throws ServiceException;

    /**
     * Find all epicrisis by owner
     * @param patientID  patient's id who owns epicrisis
     * @return list of found epicrisis
     * @throws ServiceException if a service exception occurred while processing
     */
    List<EpicrisisDTO> getEpicrisisByPatientId(long patientID) throws ServiceException;

    /**
     * Update information about epicrisis
     * @param epicrisis epicrisis with new data to be saved
     * @throws ServiceException if a service exception occurred while processing
     */
    void update(Epicrisis epicrisis) throws ServiceException;

    /**
     * Find last epicrisis issued to the patient
     * @param patientID  patient id who owns epicrisis
     * @return found epicrisis
     * @throws ServiceException if a service exception occurred while processing
     */
    Epicrisis getLastEpicrisisByPatientId(long patientID) throws ServiceException;

}
