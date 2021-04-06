package com.hospital.dao;

import com.hospital.bean.Staff;
import com.hospital.dao.exception.DAOException;

import java.util.List;

public interface StaffDAO {
    Staff getStaffByAccount(long id) throws DAOException;

    Staff getStaffById(Long id)throws DAOException;

    List<Staff> getAllByType(Long typeId) throws DAOException;

    void update(Staff staff) throws DAOException;
}
