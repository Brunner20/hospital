package com.hospital.dao;

import com.hospital.entity.Appointment;
import com.hospital.entity.AppointmentInfo;
import com.hospital.entity.AppointmentType;

public interface DocumentationDAO {
    void addAppointment(Appointment appointment) throws DAOException;

    AppointmentInfo getAppointmentInfo(String title, AppointmentType type) throws DAOException;
}
