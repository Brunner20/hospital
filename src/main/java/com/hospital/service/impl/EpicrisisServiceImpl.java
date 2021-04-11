package com.hospital.service.impl;

import com.hospital.bean.Epicrisis;
import com.hospital.bean.dto.EpicrisisDTO;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.EpicrisisDAO;
import com.hospital.dao.exception.DAOException;
import com.hospital.service.EpicrisisService;
import com.hospital.service.exception.ServiceException;
import com.hospital.service.util.MappingUtil;
import com.hospital.service.util.UtilException;
import com.hospital.service.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EpicrisisServiceImpl implements EpicrisisService {

    private static final Logger logger = LogManager.getLogger(EpicrisisServiceImpl.class);
    private static final String INVALID = " is wrong";

    @Override
    public void addEpicrisis(Epicrisis epicrisis) throws ServiceException {
        if(epicrisis == null){
            logger.log(Level.WARN,"epicrisis"+INVALID);
            throw new ServiceException("epicrisis"+INVALID);
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
    public List<EpicrisisDTO> getEpicrisisByPatientId(long patientID) throws ServiceException {
        if (!Validator.isIdValid(patientID)) {
            logger.log(Level.WARN,patientID+INVALID);
            throw new ServiceException(patientID+INVALID);
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        EpicrisisDAO epicrisisDAO = daoProvider.getEpicrisisDAO();
        List<EpicrisisDTO> epicrisisDTOList = new ArrayList<>();
        List<Epicrisis> epicrisisList;
        try {
            epicrisisList = epicrisisDAO.getEpicrisisByPatientId(patientID);
            for(Epicrisis epicrisis:epicrisisList){
                epicrisisDTOList.add(MappingUtil.matToEpicrosisDTO(epicrisis));
            }
        } catch (DAOException | UtilException e) {
            throw new ServiceException(e);
        }
        return epicrisisDTOList;
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

    @Override
    public Epicrisis getLastEpicrisisByPatientId(long patientID) throws ServiceException {
        if (!Validator.isIdValid(patientID)) {
            logger.log(Level.WARN,patientID+INVALID);
            throw new ServiceException(patientID+INVALID);
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        EpicrisisDAO epicrisisDAO = daoProvider.getEpicrisisDAO();
        List<Epicrisis> epicrisisAll;
        try {
            epicrisisAll = epicrisisDAO.getEpicrisisByPatientId(patientID);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        Epicrisis maxEpicrisis = null;
        Date maxDate  = new Date(0L);
        for(Epicrisis epicrisis:epicrisisAll){
            if(epicrisis.getReceiptDate().getTime()>maxDate.getTime()){
                maxEpicrisis = epicrisis;
                maxDate = epicrisis.getReceiptDate();
            }
        }
        return maxEpicrisis;
    }
}
