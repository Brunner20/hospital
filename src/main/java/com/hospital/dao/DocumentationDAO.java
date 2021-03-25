package com.hospital.dao;

import com.hospital.entity.Appointment;
import com.hospital.entity.AppointmentInfo;
import com.hospital.entity.AppointmentType;

import java.util.List;

public interface DocumentationDAO {
    void addAppointment(Appointment appointment) throws DAOException;

    AppointmentInfo getAppointmentInfo(String title, AppointmentType type) throws DAOException;

    List<Appointment> getAllAppointmentsByPatientId(long patientId) throws DAOException;

    AppointmentInfo getAppointmentInfoById(long infoId) throws DAOException;
}
