package com.hospital.service.util;

import com.hospital.bean.*;
import com.hospital.bean.dto.AppointmentDTO;
import com.hospital.bean.dto.EpicrisisDTO;
import com.hospital.dao.DAOProvider;
import com.hospital.dao.StaffDAO;
import com.hospital.dao.exception.DAOException;

import java.util.ArrayList;
import java.util.List;

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

    public static EpicrisisDTO matToEpicrosisDTO(Epicrisis epicrisis) throws UtilException{
        EpicrisisDTO epicrisisDTO = new EpicrisisDTO();
        List<AppointmentDTO> appointmentDTOInEpicrisi = new ArrayList<>();
        Patient patient = null;
        try {
            List<Appointment> appointmentsInEpicrisis  = DAOProvider.getInstance()
                    .getAppointmentDAO().getAllAppointmentBetweenDate(epicrisis.getReceiptDate(),epicrisis.getDischargeDate());
            patient = DAOProvider.getInstance().getPatientDAO().getPatientById(epicrisis.getPatientId());
            for(Appointment appointment: appointmentsInEpicrisis){
                appointmentDTOInEpicrisi.add(mapToAppointmentDTO(appointment));
            }
        } catch (DAOException e) {
            throw new UtilException(e);
        }

        epicrisisDTO.setId(epicrisis.getId());
        epicrisisDTO.setReceiptDate(epicrisis.getReceiptDate());
        epicrisisDTO.setDischargeDate(epicrisis.getDischargeDate());
        epicrisisDTO.setPreliminaryDiagnosis(epicrisis.getPreliminaryDiagnosis());
        epicrisisDTO.setDefinitiveDiagnosis(epicrisis.getDefinitiveDiagnosis());
        epicrisisDTO.setPatientFirstname(patient.getFirstname());
        epicrisisDTO.setPatientLastname(patient.getLastname());
        epicrisisDTO.setAppointmentList(appointmentDTOInEpicrisi);
        return epicrisisDTO;
    }
}
