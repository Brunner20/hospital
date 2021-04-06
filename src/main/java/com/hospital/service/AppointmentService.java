package com.hospital.service;

import com.hospital.bean.Appointment;
import com.hospital.bean.AppointmentInfo;
import com.hospital.bean.AppointmentStatus;
import com.hospital.bean.AppointmentType;
import com.hospital.bean.dto.AppointmentDTO;
import com.hospital.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;


public interface AppointmentService {

   void  addAppointment(Appointment appointment) throws ServiceException;

   AppointmentInfo getAppointmentInfo(String title, AppointmentType type) throws ServiceException;

    List<AppointmentDTO> getAllAppointmentsByPatientId(long patientId) throws ServiceException;

    List<AppointmentDTO> getAllAppointmentsByStaffId(long staffId) throws ServiceException;

    void updateAppointmentStatus(Long appointmentId, AppointmentStatus appointmentStatus) throws ServiceException;

    List<Appointment> getAllAppointmentBetweenDate(Date dateFrom,Date dateTo)throws ServiceException;

    void updateAppointmentEpirisis(List<Appointment> appointmentList, long epicrisisId) throws ServiceException;
}
