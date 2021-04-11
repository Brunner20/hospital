package com.hospital.dao;

import com.hospital.bean.Staff;
import com.hospital.dao.exception.DAOException;

import java.util.List;

/**
 * The interface Staff dao.
 */
public interface StaffDAO {

    /**
     * Find staff bu account id
     * @param id  the account of staff id
     * @return found staff
     * @throws DAOException if an dao exception occurred while processing
     */
    Staff getStaffByAccount(long id) throws DAOException;

    /**
     * Find staff by id
     * @param id the staff id
     * @return found staff
     * @throws DAOException if an dao exception occurred while processing
     */
    Staff getStaffById(Long id)throws DAOException;

    /**
     * Find all staff of given type
     * @param typeId the type of staff id
     * @return Staff list by id
     * @throws DAOException if an dao exception occurred while processing
     */
    List<Staff> getAllByType(Long typeId) throws DAOException;


    /**
     * Update information about staff
     * @param staff staff with new data to be saved
     * @throws DAOException if an dao exception occurred while processing
     */
    void update(Staff staff) throws DAOException;
}
