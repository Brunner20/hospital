package com.hospital.service.impl;

import com.hospital.dao.DAOException;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.EpicrisisDAO;
import com.hospital.entity.Epicrisis;
import com.hospital.service.EpicrisisService;
import com.hospital.service.ServiceException;
import com.hospital.service.validation.Validator;

public class EpicrisisServiceImpl implements EpicrisisService {
    @Override
    public void addEpicrisis(Epicrisis epicrisis) throws ServiceException {
        if(epicrisis == null){
            throw new ServiceException("epicrisis is null");
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        EpicrisisDAO epicrisisDAO = daoProvider.getEpicrisisDAO();
        try {
            epicrisisDAO.addEpicrisis(epicrisis);
        } catch (DAOException e) {
           throw new ServiceException(e);
        }


    }

    @Override
    public Epicrisis getEpicrisisByPatientId(long patientID) throws ServiceException {
        if (!Validator.isIdValid(patientID)) {
            throw new ServiceException("wrong id");
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        EpicrisisDAO epicrisisDAO = daoProvider.getEpicrisisDAO();

        Epicrisis epicrisis = new Epicrisis();
        try {
            epicrisis = epicrisisDAO.getEpicrisisByPatientId(patientID);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return epicrisis;
    }

    @Override
    public void update(Epicrisis epicrisis) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        EpicrisisDAO epicrisisDAO = daoProvider.getEpicrisisDAO();
        try {
            epicrisisDAO.update(epicrisis);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
