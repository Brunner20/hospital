package com.hospital.dao;

import com.hospital.dao.exception.DAOException;
import com.hospital.entity.Appointment;
import com.hospital.entity.AppointmentInfo;
import com.hospital.entity.AppointmentStatus;
import com.hospital.entity.AppointmentType;

import java.sql.Date;
import java.util.List;

public interface AppointmentDAO {
    void addAppointment(Appointment appointment) throws DAOException;

    AppointmentInfo getAppointmentInfo(String title, AppointmentType type) throws DAOException;

    List<Appointment> getAllAppointmentsByPatientId(long patientId) throws DAOException;

    AppointmentInfo getAppointmentInfoById(long infoId) throws DAOException;

    List<Appointment> getAllAppointmentsByStaffId(long staffId) throws DAOException;

    void updateAppointmentStatus(Long appointmentId, AppointmentStatus appointmentStatus) throws DAOException;

    List<Appointment> getAllAppointmentBetweenDate(Date dateFrom, Date dateTo)throws DAOException;

    void update(Appointment appointment) throws DAOException;
}
