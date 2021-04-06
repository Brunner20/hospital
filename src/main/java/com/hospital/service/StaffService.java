package com.hospital.service;

import com.hospital.entity.Staff;
import com.hospital.service.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;

public interface StaffService {



    Staff getStaffById(Long id) throws ServiceException;

    Staff getStaffByAccount(long id) throws ServiceException;

    List<Staff> getAllByType(Long typeId) throws ServiceException;

    void update(Staff staff) throws ServiceException;

    void savePictureToStaff(Staff staff, Part part) throws ServiceException;
}
