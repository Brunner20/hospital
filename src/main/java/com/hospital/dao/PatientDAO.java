package com.hospital.dao;

import com.hospital.entity.Patient;

public interface PatientDAO {

    void update(Patient patient) throws DAOException;

    void updateAge(long id,int age) throws DAOException;
}
