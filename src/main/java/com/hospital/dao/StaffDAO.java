package com.hospital.dao;

import com.hospital.entity.Patient;
import com.hospital.entity.Staff;

import java.util.List;

public interface StaffDAO {
    void update(Staff staff) throws DAOException;
    List<Patient> getAllPatientsByStaff(long id) throws DAOException;
}
