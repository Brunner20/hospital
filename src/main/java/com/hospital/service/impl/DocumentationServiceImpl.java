package com.hospital.service.impl;

import com.hospital.dao.DAOException;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.DocumentationDAO;
import com.hospital.entity.Appointment;
import com.hospital.entity.AppointmentInfo;
import com.hospital.entity.AppointmentType;
import com.hospital.service.DocumentationService;
import com.hospital.service.ServiceException;

public class DocumentationServiceImpl implements DocumentationService {

    @Override
    public void addAppointment(Appointment appointment) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        DocumentationDAO documentationDAO = daoProvider.getDocumentationDAO();
        try {
            documentationDAO.addAppointment(appointment);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public AppointmentInfo getAppointmentInfo(String title, AppointmentType type) throws ServiceException {

        if(title.isEmpty()||type==null){
            throw new ServiceException("title is wrong");
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        DocumentationDAO documentationDAO = daoProvider.getDocumentationDAO();
        AppointmentInfo appointmentInfo = null;
        try {
          appointmentInfo = documentationDAO.getAppointmentInfo(title,type);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return appointmentInfo;
    }
}
