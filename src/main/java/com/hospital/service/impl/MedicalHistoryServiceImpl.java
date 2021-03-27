package com.hospital.service.impl;

import com.hospital.dao.DAOException;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.MedicalHistoryDAO;
import com.hospital.entity.MedicalHistory;
import com.hospital.service.MedicalHistoryService;
import com.hospital.service.ServiceException;
import com.hospital.service.validation.Validator;


public class MedicalHistoryServiceImpl implements MedicalHistoryService {
    @Override
    public void add(MedicalHistory medicalHistory) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        MedicalHistoryDAO medicalHistoryDAO = daoProvider.getMedicalHistoryDAO();
        try {
             medicalHistoryDAO.add(medicalHistory);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public MedicalHistory getByPatientId(long patientId) throws ServiceException {
      if(!Validator.isIdValid(patientId)){
          throw new ServiceException("wrong id");
      }
        DAOProvider daoProvider = DAOProvider.getInstance();
        MedicalHistoryDAO medicalHistoryDAO = daoProvider.getMedicalHistoryDAO();
        MedicalHistory medicalHistory = null;
        try {
            medicalHistory = medicalHistoryDAO.getByPatientId(patientId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return medicalHistory;
    }

    @Override
    public void update(MedicalHistory medicalHistory) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        MedicalHistoryDAO medicalHistoryDAO = daoProvider.getMedicalHistoryDAO();
        try {
            medicalHistoryDAO.update(medicalHistory);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
