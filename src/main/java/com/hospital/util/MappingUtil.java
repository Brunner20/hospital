package com.hospital.util;

import com.hospital.dao.DAOException;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.StaffDAO;
import com.hospital.entity.Appointment;
import com.hospital.entity.AppointmentInfo;
import com.hospital.entity.Patient;
import com.hospital.entity.Staff;
import com.hospital.entity.dto.AppointmentDTO;

public class MappingUtil {

    public static AppointmentDTO mapToAppointmentDTO(Appointment appointment) throws UtilException {
        AppointmentDTO dto = new AppointmentDTO();

        Patient patient;
        Staff appointStaff;
        Staff execStaff;
        AppointmentInfo appointmentInfo;
        try {
            patient = DAOProvider.getInstance().getPatientDAO().getPatientById(appointment.getPatientId());
            StaffDAO staffDAO = DAOProvider.getInstance().getStaffDAO();
            appointStaff = staffDAO.getStaffById(appointment.getAppointingDoctorId());
            execStaff = staffDAO.getStaffById(appointment.getExecuteStaffId());
            appointmentInfo = DAOProvider.getInstance().getAppointmentDAO().getAppointmentInfoById(appointment.getInfoId());
        } catch (DAOException e) {
            throw new UtilException(e);
        }
        dto.setId(appointment.getId());
        dto.setDateOfAppointment(appointment.getDateOfAppointment());
        dto.setDateOfCompletion(appointment.getDateOfCompletion());
        dto.setInfo(appointmentInfo.getInfo());
        dto.setStatus(appointment.getStatus());
        dto.setType(appointmentInfo.getType());
        dto.setAppointingDoctorFirstname(appointStaff.getFirstname());
        dto.setAppointingDoctorLastname(appointStaff.getLastname());
        dto.setExecuteStaffFirstname(execStaff.getFirstname());
        dto.setExecuteStaffLastname(execStaff.getLastname());
        dto.setPatientFirstname(patient.getFirstname());
        dto.setPatientLastname(patient.getLastname());
        return dto;

    }
}
