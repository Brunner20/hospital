package com.hospital.service.impl;

import com.hospital.bean.MedicalHistory;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.MedicalHistoryDAO;
import com.hospital.dao.exception.DAOException;
import com.hospital.service.MedicalHistoryService;
import com.hospital.service.exception.ServiceException;
import com.hospital.service.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class containing business logic to work with medical history
 */
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private static final Logger logger = LogManager.getLogger(MedicalHistoryServiceImpl.class);
    private static final String INVALID = " is wrong";

    /**
     * Instance of {@link DAOProvider}
     */
    private static final DAOProvider provider  = DAOProvider.getInstance();

    @Override
    public void add(MedicalHistory medicalHistory) throws ServiceException {
        MedicalHistoryDAO medicalHistoryDAO = provider.getMedicalHistoryDAO();
        try {
             medicalHistoryDAO.add(medicalHistory);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public MedicalHistory getByPatientId(long patientId) throws ServiceException {
      if(!Validator.isIdValid(patientId)){
          logger.log(Level.WARN,patientId+INVALID);
          throw new ServiceException(patientId+INVALID);
      }
        MedicalHistoryDAO medicalHistoryDAO = provider.getMedicalHistoryDAO();
        MedicalHistory medicalHistory;
        try {
            medicalHistory = medicalHistoryDAO.getByPatientId(patientId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return medicalHistory;
    }

    @Override
    public void update(MedicalHistory medicalHistory) throws ServiceException {
        MedicalHistoryDAO medicalHistoryDAO = provider.getMedicalHistoryDAO();
        try {
            medicalHistoryDAO.update(medicalHistory);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
