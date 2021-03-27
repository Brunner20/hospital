package com.hospital.service;

import com.hospital.entity.MedicalHistory;

public interface MedicalHistoryService {

    void add(MedicalHistory medicalHistory) throws ServiceException;

    MedicalHistory getByPatientId(long patientId) throws ServiceException;

    void  update(MedicalHistory medicalHistory) throws ServiceException;
}
