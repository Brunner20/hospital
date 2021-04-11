package com.hospital.dao;

import com.hospital.bean.Appointment;
import com.hospital.bean.AppointmentInfo;
import com.hospital.bean.AppointmentStatus;
import com.hospital.bean.AppointmentType;
import com.hospital.dao.exception.DAOException;

import java.sql.Date;
import java.util.List;

/**
 * The interface appointment dao.
 */
public interface AppointmentDAO {
    /**
     * Add new appointment
     * @param appointment appointment to save
     * @throws DAOException if an dao exception occurred while processing
     */
    void addAppointment(Appointment appointment) throws DAOException;

    /**
     * Find appointmentInfo by title and appointment type.If appointmentInfo does not exists then creates a new one
     * @param title tile of appointment to find
     * @param type  type of appointment to find
     * @return found appointmentInfo
     * @throws DAOException if an dao exception occurred while processing
     */
    AppointmentInfo getAppointmentInfo(String title, AppointmentType type) throws DAOException;

    /**
     * Find all Appointment of patient
     * @param patientId the patient id
     * @return patient appointment list
     * @throws DAOException if an dao exception occurred while processing
     */
    List<Appointment> getAllAppointmentsByPatientId(long patientId) throws DAOException;

    /**
     *  Find appointmentInfo by id
     * @param infoId the appointmentInfo id
     * @return  found AppointmentInfo
     * @throws DAOException if an dao exception occurred while processing
     */
    AppointmentInfo getAppointmentInfoById(long infoId) throws DAOException;

    /**
     * Find  all appointment assigned by the given staff
     * @param staffId the staff id
     * @return  appointment list
     * @throws DAOException if an dao exception occurred while processing
     */
    List<Appointment> getAllAppointmentsByStaffId(long staffId) throws DAOException;

    /**
     * Update appointmentStatus about appointment
     * @param appointmentId the appointment id which needs to be updated
     * @param appointmentStatus туц appointment status
     * @throws DAOException if an dao exception occurred while processing
     */
    void updateAppointmentStatus(Long appointmentId, AppointmentStatus appointmentStatus) throws DAOException;

    /**
     * Find all appointment between two date
     * @param dateFrom date from which the search takes place
     * @param dateTo the end date of which the search takes place
     * @param patientId the patient id
     * @return found appointment list
     * @throws DAOException if an dao exception occurred while processing
     */
    List<Appointment> getAllAppointmentBetweenDate(Date dateFrom, Date dateTo, long patientId)throws DAOException;

    /**
     * Update information about appointment
     * @param appointment  appointment with new data to be saved
     * @throws DAOException if an dao exception occurred while processing
     */
    void update(Appointment appointment) throws DAOException;
}
