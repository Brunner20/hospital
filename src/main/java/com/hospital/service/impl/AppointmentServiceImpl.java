package com.hospital.service.impl;

import com.hospital.bean.Appointment;
import com.hospital.bean.AppointmentInfo;
import com.hospital.bean.AppointmentStatus;
import com.hospital.bean.AppointmentType;
import com.hospital.bean.dto.AppointmentDTO;
import com.hospital.dao.AppointmentDAO;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.exception.DAOException;
import com.hospital.service.AppointmentService;
import com.hospital.service.exception.DataFormatServiceException;
import com.hospital.service.exception.ServiceException;
import com.hospital.service.util.MappingUtil;
import com.hospital.service.util.UtilException;
import com.hospital.service.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger logger = LogManager.getLogger(AppointmentServiceImpl.class);
    private static final String INVALID = " is wrong";
    @Override
    public void addAppointment(Appointment appointment) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        AppointmentDAO appointmentDAO = daoProvider.getAppointmentDAO();
        try {
            appointmentDAO.addAppointment(appointment);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public AppointmentInfo getAppointmentInfo(String title, AppointmentType type) throws ServiceException {
        if(!Validator.isTitleValid(title)){
            logger.log(Level.WARN,title+INVALID);
            throw new DataFormatServiceException(title+INVALID);
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        AppointmentDAO appointmentDAO = daoProvider.getAppointmentDAO();
        AppointmentInfo appointmentInfo;
        try {
          appointmentInfo = appointmentDAO.getAppointmentInfo(title,type);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return appointmentInfo;
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByPatientId(long patientId) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        AppointmentDAO appointmentDAO = daoProvider.getAppointmentDAO();
        List<Appointment> appointmentsByPatient;
        List<AppointmentDTO> dtoList;
        try {
            appointmentsByPatient = appointmentDAO.getAllAppointmentsByPatientId(patientId);
            dtoList = appointmentsByPatient.stream().map(MappingUtil::mapToAppointmentDTO)
                    .collect(Collectors.toList());
        } catch (DAOException | UtilException e) {
           throw new ServiceException(e);
        }

        return dtoList;
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByStaffId(long staffId) throws ServiceException {

        if(!Validator.isIdValid(staffId)){
            logger.log(Level.WARN,staffId+INVALID);
            throw new ServiceException(staffId+INVALID);
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        AppointmentDAO appointmentDAO = daoProvider.getAppointmentDAO();
        List<Appointment> appointmentsByPatient;
        List<AppointmentDTO> dtoList;
        try {
            appointmentsByPatient = appointmentDAO.getAllAppointmentsByStaffId(staffId);
            dtoList = appointmentsByPatient.stream().map(MappingUtil::mapToAppointmentDTO)
                    .collect(Collectors.toList());
        } catch (DAOException | UtilException e) {
            throw new ServiceException(e);
        }
        return dtoList;
    }

    @Override
    public void updateAppointmentStatus(Long appointmentId, AppointmentStatus appointmentStatus) throws ServiceException {
        if(!Validator.isIdValid(appointmentId)){
            logger.log(Level.WARN,appointmentId+INVALID);
            throw new ServiceException(appointmentId+INVALID);
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        AppointmentDAO appointmentDAO = daoProvider.getAppointmentDAO();
        try {
            appointmentDAO.updateAppointmentStatus(appointmentId,appointmentStatus);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Appointment> getAllAppointmentBetweenDate(Date dateFrom, Date dateTo, long id) throws ServiceException {
        if(dateFrom==null || dateTo == null){
            logger.log(Level.WARN,dateFrom+" "+dateTo+INVALID);
            throw new ServiceException(dateFrom+" "+dateTo+INVALID);
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        AppointmentDAO appointmentDAO = daoProvider.getAppointmentDAO();
        List<Appointment> appointments;
        try {
            appointments = appointmentDAO.getAllAppointmentBetweenDate(dateFrom,dateTo,id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return appointments;
    }

    @Override
    public void updateAppointmentEpirisis(List<Appointment> appointmentList, long epicrisisId) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        AppointmentDAO appointmentDAO = daoProvider.getAppointmentDAO();
        for(Appointment appointment:appointmentList){
            appointment.setEpicrisisID(epicrisisId);
            try {
                appointmentDAO.update(appointment);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
    }
}
