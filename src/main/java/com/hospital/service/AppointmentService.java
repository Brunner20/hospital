package com.hospital.service;

import com.hospital.entity.Appointment;
import com.hospital.entity.AppointmentInfo;
import com.hospital.entity.AppointmentStatus;
import com.hospital.entity.AppointmentType;
import com.hospital.entity.dto.AppointmentDTO;

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
