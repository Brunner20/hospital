package com.hospital.service;

import com.hospital.entity.Patient;
import com.hospital.entity.Staff;

import java.util.List;

public interface StaffService {

    List<Patient> getAllPatientsByStaff(long id) throws ServiceException;

    Staff getStaffById(Long id) throws ServiceException;

    List<Staff> getAll() throws ServiceException;
}
