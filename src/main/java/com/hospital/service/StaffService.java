package com.hospital.service;

import com.hospital.entity.Staff;

import java.util.List;

public interface StaffService {



    Staff getStaffById(Long id) throws ServiceException;

    List<Staff> getAll() throws ServiceException;
}
