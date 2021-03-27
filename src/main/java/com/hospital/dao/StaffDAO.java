package com.hospital.dao;

import com.hospital.entity.Staff;

import java.util.List;

public interface StaffDAO {
    void update(Staff staff) throws DAOException;

    Staff getStaffById(Long id)throws DAOException;

    List<Staff> getAll() throws DAOException;
}
