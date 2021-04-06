package com.hospital.dao;

import com.hospital.bean.MedicalHistory;
import com.hospital.dao.exception.DAOException;

public interface MedicalHistoryDAO {

    void add(MedicalHistory medicalHistory) throws DAOException;

    MedicalHistory getByPatientId(long patientId) throws DAOException;

    void  update(MedicalHistory medicalHistory) throws DAOException;
}
