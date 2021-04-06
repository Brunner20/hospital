package com.hospital.service.impl;

import com.hospital.dao.DAOProvider;
import com.hospital.dao.PatientDAO;
import com.hospital.dao.exception.DAOException;
import com.hospital.entity.Patient;
import com.hospital.service.PatientService;
import com.hospital.service.exception.ServiceException;
import com.hospital.service.validation.Validator;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PatientServiceImpl implements PatientService {

    private static final String WRONG_ID = "wrong id";
    private static final String PATH_TO_STORAGE = ResourceBundle.getBundle("application").getString("application.path_to_storage_in_server");
    private static final String PATH_TO_STORAGE_IN_USER = ResourceBundle.getBundle("application").getString("application.path_to_storage_in_db");
    private static final String PATH_TO_STORAGE_DEFAULT = ResourceBundle.getBundle("application").getString("application.path_to_default_image");




    @Override
    public List<Patient> getFreePatients() throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        PatientDAO patientDAO = daoProvider.getPatientDAO();
        List<Patient> patients = new ArrayList<>();
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
            throw new ServiceException(WRONG_ID);
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
        List<Patient> patients = new ArrayList<>();
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
            throw new ServiceException("id not valid");
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        PatientDAO patientDAO = daoProvider.getPatientDAO();
        Patient patient = null;
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
        Patient patient = null;
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
        if(part == null){
            patient.setPatientPic(PATH_TO_STORAGE_DEFAULT);
        }else {
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            File uploads = new File(PATH_TO_STORAGE);
            File file = new File(uploads, fileName);
            try (InputStream fileContent = part.getInputStream();) {
                Files.copy(fileContent, file.toPath());
            } catch (IOException e) {
                throw new ServiceException(e);
            }
            patient.setPatientPic(PATH_TO_STORAGE_IN_USER+fileName);
        }
        update(patient);
    }

}
