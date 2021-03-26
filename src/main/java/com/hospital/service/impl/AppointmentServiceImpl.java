package com.hospital.service.impl;

import com.hospital.dao.AppointmentDAO;
import com.hospital.dao.DAOException;
import com.hospital.dao.DAOProvider;
import com.hospital.entity.Appointment;
import com.hospital.entity.AppointmentInfo;
import com.hospital.entity.AppointmentStatus;
import com.hospital.entity.AppointmentType;
import com.hospital.entity.dto.AppointmentDTO;
import com.hospital.service.AppointmentService;
import com.hospital.service.ServiceException;
import com.hospital.service.validation.Validator;
import com.hospital.util.MappingUtil;
import com.hospital.util.UtilException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentServiceImpl implements AppointmentService {

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

        if(title.isEmpty()||type==null){
            throw new ServiceException("title is wrong");
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        AppointmentDAO appointmentDAO = daoProvider.getAppointmentDAO();
        AppointmentInfo appointmentInfo = null;
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
        List<Appointment> appointmentsByPatient = new ArrayList<>();
        List<AppointmentDTO> dtoList = new ArrayList<>();
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
            throw new ServiceException("wrong id");
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        AppointmentDAO appointmentDAO = daoProvider.getAppointmentDAO();
        List<Appointment> appointmentsByPatient = new ArrayList<>();
        List<AppointmentDTO> dtoList = new ArrayList<>();
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
            throw new ServiceException("wrong id");
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
    public List<Appointment> getAllAppointmentBetweenDate(Date dateFrom, Date dateTo) throws ServiceException {
        if(dateFrom==null || dateTo == null){
            throw new ServiceException("wrong date");
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        AppointmentDAO appointmentDAO = daoProvider.getAppointmentDAO();
        List<Appointment> appointments = new ArrayList<>();

        try {
            appointments = appointmentDAO.getAllAppointmentBetweenDate(dateFrom,dateTo);
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
