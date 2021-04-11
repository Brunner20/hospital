package com.hospital.service.impl;

import com.hospital.bean.Patient;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.PatientDAO;
import com.hospital.dao.exception.DAOException;
import com.hospital.service.PatientService;
import com.hospital.service.exception.ServiceException;
import com.hospital.service.util.UploadUtil;
import com.hospital.service.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.util.List;

/**
 * The class containing business logic to work with patient
 */
public class PatientServiceImpl implements PatientService {


    private static final Logger logger = LogManager.getLogger(PatientServiceImpl.class);
    private static final String INVALID = " is wrong";

    @Override
    public List<Patient> getFreePatients() throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        PatientDAO patientDAO = daoProvider.getPatientDAO();
        List<Patient> patients;
        try {
           patients = patientDAO.getFreePatients();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return patients;
    }

    @Override
    public List<Patient> getAllPatientsByStaff(long id) throws ServiceException {
        if (!Validator.isIdValid(id)) {
            logger.log(Level.WARN,id+INVALID);
            throw new ServiceException(id+INVALID);
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        PatientDAO patientDAO = daoProvider.getPatientDAO();
        List<Patient> patients;
        try {
            patients = patientDAO.getAllPatientsByStaff(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return patients;
    }

    @Override
    public List<Patient> getAll() throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        PatientDAO patientDAO = daoProvider.getPatientDAO();
        List<Patient> patients;
        try {
            patients = patientDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return patients;
    }

    @Override
    public Patient getPatientById(Long id) throws ServiceException {
        if (!Validator.isIdValid(id)) {
            logger.log(Level.WARN,id+INVALID);
            throw new ServiceException(id+INVALID);
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        PatientDAO patientDAO = daoProvider.getPatientDAO();
        Patient patient;
        try {
            patient = patientDAO.getPatientById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return patient;
    }

    @Override
    public Patient getPatientByAccount(long accountId) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        PatientDAO patientDAO = daoProvider.getPatientDAO();
        Patient patient;
        try {
            patient = patientDAO.getPatientByAccount(accountId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return patient;
    }

    @Override
    public void update(Patient patient) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        PatientDAO patientDAO = daoProvider.getPatientDAO();
        try {
            patientDAO.update(patient);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void savePictureToPatient(Patient patient, Part part) throws ServiceException {
       String file = UploadUtil.upload(part);
       patient.setPatientPic(file);
       update(patient);
    }

}
