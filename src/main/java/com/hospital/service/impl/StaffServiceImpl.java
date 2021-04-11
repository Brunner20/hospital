package com.hospital.service.impl;

import com.hospital.bean.Staff;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.StaffDAO;
import com.hospital.dao.exception.DAOException;
import com.hospital.service.StaffService;
import com.hospital.service.exception.ServiceException;
import com.hospital.service.util.UploadUtil;
import com.hospital.service.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.util.List;

/**
 * The class containing business logic to work with staff
 */
public class StaffServiceImpl implements StaffService {

    private static final Logger logger = LogManager.getLogger(StaffServiceImpl.class);
    private static final String INVALID = " is wrong";

    @Override
    public Staff getStaffById(Long id) throws ServiceException {
        if (!Validator.isIdValid(id)) {
            logger.log(Level.WARN,id+INVALID);
            throw new ServiceException(id+INVALID);
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        StaffDAO staffDAO = daoProvider.getStaffDAO();
        Staff staff;
        try {
            staff = staffDAO.getStaffById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return staff;
    }

    @Override
    public Staff getStaffByAccount(long id) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        StaffDAO staffDAO = daoProvider.getStaffDAO();
        Staff staff;
        try {
            staff = staffDAO.getStaffByAccount(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return staff;
    }


    @Override
    public List<Staff> getAllByType(Long typeId) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        StaffDAO staffDAO = daoProvider.getStaffDAO();
        List<Staff> allStaff;
        try {
            allStaff = staffDAO.getAllByType(typeId);
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
        String file = UploadUtil.upload(part);
        staff.setPicture(file);
        update(staff);
    }
}
