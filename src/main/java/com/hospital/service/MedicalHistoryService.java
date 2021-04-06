package com.hospital.service;

import com.hospital.bean.MedicalHistory;
import com.hospital.service.exception.ServiceException;

public interface MedicalHistoryService {

    void add(MedicalHistory medicalHistory) throws ServiceException;

    MedicalHistory getByPatientId(long patientId) throws ServiceException;

    void  update(MedicalHistory medicalHistory) throws ServiceException;
}
