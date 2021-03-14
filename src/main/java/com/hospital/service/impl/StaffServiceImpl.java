package com.hospital.service.impl;

import com.hospital.dao.DAOException;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.StaffDAO;
import com.hospital.entity.Patient;
import com.hospital.service.ServiceException;
import com.hospital.service.StaffService;
import com.hospital.service.validation.Validator;

import java.util.List;

public class StaffServiceImpl implements StaffService {

    private static final String WRONG_ID = "wrong id";

    @Override
    public List<Patient> getAllPatients(long id) throws ServiceException {
        if (!Validator.isIdValid(id)) {
            throw new ServiceException(WRONG_ID);
        }

        DAOProvider daoProvider = DAOProvider.getInstance();

        StaffDAO staffDAO = daoProvider.getStaffDAO();

        List<Patient> patients;
        try {
             patients = staffDAO.getAllPatientsByStaff(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return patients;


    }
}
