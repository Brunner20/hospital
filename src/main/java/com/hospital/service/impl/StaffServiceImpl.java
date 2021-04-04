package com.hospital.service.impl;

import com.hospital.dao.DAOProvider;
import com.hospital.dao.StaffDAO;
import com.hospital.dao.exception.DAOException;
import com.hospital.entity.Staff;
import com.hospital.service.StaffService;
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

public class StaffServiceImpl implements StaffService {

    private static final String WRONG_ID = "wrong id";

    private static final String PATH_TO_STORAGE = ResourceBundle.getBundle("application").getString("application.path_to_storage_in_server");
    private static final String PATH_TO_STORAGE_IN_USER = ResourceBundle.getBundle("application").getString("application.path_to_storage_in_db");
    private static final String PATH_TO_STORAGE_DEFAULT = ResourceBundle.getBundle("application").getString("application.path_to_default_image");


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

    @Override
    public void update(Staff staff) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        StaffDAO staffDAO = daoProvider.getStaffDAO();
        try {
           staffDAO.update(staff);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void savePictureToStaff(Staff staff, Part part) throws ServiceException {

        if(part == null){
          staff.setPicture(PATH_TO_STORAGE_DEFAULT);
        }else {
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            File uploads = new File(PATH_TO_STORAGE);
            File file = new File(uploads, fileName);
            try (InputStream fileContent = part.getInputStream();) {
                Files.copy(fileContent, file.toPath());
            } catch (IOException e) {
               throw new ServiceException(e);
            }
            staff.setPicture(PATH_TO_STORAGE_IN_USER);
        }
        update(staff);
    }
}
