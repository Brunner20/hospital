package com.hospital.service.impl;

import com.hospital.dao.DAOException;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.PatientDAO;
import com.hospital.service.PatientService;
import com.hospital.service.ServiceException;
import com.hospital.service.validation.Validator;

public class PatientServiceImpl implements PatientService {
    @Override
    public void updateAge(long id, String age) throws ServiceException {

        if(!Validator.isAgeValid(age)||!Validator.isIdValid(id)){
            throw new ServiceException("age not valid");
        }

        int ageInt = Integer.parseInt(age);
        DAOProvider daoProvider = DAOProvider.getInstance();
        PatientDAO patientDAO = daoProvider.getPatientDAO();
        try {
            patientDAO.updateAge(id,ageInt);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
