package com.hospital.service;

import com.hospital.bean.Appointment;
import com.hospital.bean.AppointmentInfo;
import com.hospital.bean.AppointmentStatus;
import com.hospital.bean.AppointmentType;
import com.hospital.bean.dto.AppointmentDTO;
import com.hospital.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;

/**
 * The interface appointment  service.
 */
public interface AppointmentService {

    /**
     * Add new appointment
     * @param appointment appointment to save
     * @throws ServiceException if a service exception occurred while processing
     */
   void  addAppointment(Appointment appointment) throws ServiceException;

    /**
     * Find appointmentInfo by title and appointment type.
     * @param title tile of appointment to find
     * @param type  type of appointment to find
     * @return found appointmentInfo
     * @throws ServiceException if a service exception occurred while processing
     */
   AppointmentInfo getAppointmentInfo(String title, AppointmentType type) throws ServiceException;

    /**
     * Find all Appointment of patient
     * @param patientId the patient id
     * @return patient appointment list
     * @throws ServiceException if a service exception occurred while processing
     */
    List<AppointmentDTO> getAllAppointmentsByPatientId(long patientId) throws ServiceException;

    /**
     * Find  all appointment assigned by the given staff
     * @param staffId the staff id
     * @return  appointment list
     * @throws ServiceException if a service exception occurred while processing
     */
    List<AppointmentDTO> getAllAppointmentsByStaffId(long staffId) throws ServiceException;

    /**
     * Update appointmentStatus about appointment
     * @param appointmentId the appointment id which needs to be updated
     * @param appointmentStatus туц appointment status
     * @throws ServiceException if a service exception occurred while processing
     */
    void updateAppointmentStatus(Long appointmentId, AppointmentStatus appointmentStatus) throws ServiceException;

    /**
     * Find all appointment between two date
     * @param dateFrom date from which the search takes place
     * @param dateTo the end date of which the search takes place
     * @param id the patient id
     * @return found appointment list
     * @throws ServiceException if a service exception occurred while processing
     */
    List<Appointment> getAllAppointmentBetweenDate(Date dateFrom, Date dateTo, long id)throws ServiceException;


    /**
     * Enter all appointment in the epicrisis
     * @param appointmentList list of appointments
     * @param epicrisisId the epicrisis id
     * @throws ServiceException if a service exception occurred while processing
     */
    void updateAppointmentEpicrisis(List<Appointment> appointmentList, long epicrisisId) throws ServiceException;
}
