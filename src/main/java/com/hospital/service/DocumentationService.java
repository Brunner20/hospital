package com.hospital.service;

import com.hospital.entity.Appointment;
import com.hospital.entity.AppointmentInfo;
import com.hospital.entity.AppointmentType;


public interface DocumentationService {

   void  addAppointment(Appointment appointment) throws ServiceException;

   AppointmentInfo getAppointmentInfo(String title, AppointmentType type) throws ServiceException;
}
