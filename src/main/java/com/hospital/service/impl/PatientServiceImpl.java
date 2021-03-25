package com.hospital.service.impl;

import com.hospital.dao.DAOException;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.PatientDAO;
import com.hospital.entity.Patient;
import com.hospital.service.PatientService;
import com.hospital.service.ServiceException;
import com.hospital.service.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class PatientServiceImpl implements PatientService {

    private static final String WRONG_ID = "wrong id";

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
    public void updateDoctor(List<String> selectedPatientsIds, Long doctorId) throws ServiceException {

        if(!Validator.isIdValid(doctorId)){
            throw new ServiceException("id not valid");
        }
        for(String patientsId: selectedPatientsIds) {
            if(!Validator.isIdValid(patientsId)){
                throw new ServiceException("id not valid");
            }
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        PatientDAO patientDAO = daoProvider.getPatientDAO();
        try {
             patientDAO.updateDoctor(selectedPatientsIds,doctorId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

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

}
