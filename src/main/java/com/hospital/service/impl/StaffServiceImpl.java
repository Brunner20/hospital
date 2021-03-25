package com.hospital.service.impl;

import com.hospital.dao.DAOException;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.StaffDAO;
import com.hospital.entity.Staff;
import com.hospital.service.ServiceException;
import com.hospital.service.StaffService;
import com.hospital.service.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class StaffServiceImpl implements StaffService {

    private static final String WRONG_ID = "wrong id";



    @Override
    public Staff getStaffById(Long id) throws ServiceException {
        if (!Validator.isIdValid(id)) {
            throw new ServiceException(WRONG_ID);
        }

        DAOProvider daoProvider = DAOProvider.getInstance();

        StaffDAO staffDAO = daoProvider.getStaffDAO();

        Staff staff = null;
        try {
            staff = staffDAO.getStaffById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return staff;

    }


    @Override
    public List<Staff> getAll() throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        StaffDAO staffDAO = daoProvider.getStaffDAO();
        List<Staff> allStaff = new ArrayList<>();
        try {
            allStaff = staffDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return allStaff;
    }
}
