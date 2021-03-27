package com.hospital.dao;

import com.hospital.entity.MedicalHistory;

public interface MedicalHistoryDAO {

    void add(MedicalHistory medicalHistory) throws DAOException;

    MedicalHistory getByPatientId(long patientId) throws DAOException;

    void  update(MedicalHistory medicalHistory) throws DAOException;
}
