package com.hospital.dao;

import com.hospital.bean.Epicrisis;
import com.hospital.dao.exception.DAOException;

import java.util.List;

/**
 * The interface epicrisis dao.
 */
public interface EpicrisisDAO {

    /**
     * Add new epicrisis
     * @param epicrisis epicrisis to save
     * @throws DAOException if an dao exception occurred while processing
     */
    void addEpicrisis(Epicrisis epicrisis) throws DAOException;

    /**
     * Find all epicrisis by owner
     * @param patientId  patient's id who owns epicrisis
     * @return list of found epicrisis
     * @throws DAOException if an dao exception occurred while processing
     */
    List<Epicrisis> getEpicrisisByPatientId(long patientId)throws DAOException;

    /**
     * Update information about epicrisis
     * @param epicrisis epicrisis with new data to be saved
     * @throws DAOException if an dao exception occurred while processing
     */
    void update(Epicrisis epicrisis) throws DAOException;
}
