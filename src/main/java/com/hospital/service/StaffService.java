package com.hospital.service;

import com.hospital.bean.Staff;
import com.hospital.service.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;

/**
 * The interface Staff service.
 */
public interface StaffService {

    /**
     * Find staff by id
     * @param id the staff id
     * @return found staff
     * @throws ServiceException if a service exception occurred while processing
     */
    Staff getStaffById(Long id) throws ServiceException;

    /**
     * Find staff bu account id
     * @param id  the account of staff id
     * @return found staff
     * @throws ServiceException if a service exception occurred while processing
     */
    Staff getStaffByAccount(long id) throws ServiceException;

    /**
     * Find all staff of given type
     * @param typeId the type of staff id
     * @return Staff list by id
     * @throws ServiceException if a service exception occurred while processing
     */
    List<Staff> getAllByType(Long typeId) throws ServiceException;

    /**
     * Update information about staff
     * @param staff staff with new data to be saved
     * @throws ServiceException if a service exception occurred while processing
     */
    void update(Staff staff) throws ServiceException;

    /**
     * Save picture of staff
     * @param staff staff who needs to save the picture
     * @param part picture data
     * @throws ServiceException if a service exception occurred while processing
     */
    void savePictureToStaff(Staff staff, Part part) throws ServiceException;
}
