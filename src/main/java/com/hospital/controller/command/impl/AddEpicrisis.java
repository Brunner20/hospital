package com.hospital.controller.command.impl;

import com.hospital.bean.Appointment;
import com.hospital.bean.Epicrisis;
import com.hospital.bean.MedicalHistory;
import com.hospital.bean.Patient;
import com.hospital.controller.command.Command;
import com.hospital.service.*;
import com.hospital.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static com.hospital.controller.command.CommandParameter.*;

public class AddEpicrisis implements Command {


    private static final String DISCHARGE_DATE = "dischargeDate";
    private static final String DEFINITIVE_DIAGNOSIS = "definitiveDiagnosis";
    private static final String PATIENT_ID = "patient_id";


    private static final String EPICRISIS_ADDED_OK = "local.info.epicrisis_added";
    private static final String EPICRISIS_ADDED_ERROR = "local.error.epicrisis_not_added";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if(session == null) {
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }
        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        String role  = (String) session.getAttribute(ATTRIBUTE_ROLE);
        if (isAuth == null || !isAuth || role.equals(ROLE_PATIENT)) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        Date dischargeDate = Date.valueOf(request.getParameter(DISCHARGE_DATE));
        String  definitiveDiagnosis = request.getParameter(DEFINITIVE_DIAGNOSIS);
        long patientID = Long.parseLong(request.getParameter(PATIENT_ID));

        ServiceProvider provider = ServiceProvider.getInstance();
        EpicrisisService epicrisisService = provider.getEpicrisisService();
        AppointmentService appointmentService = provider.getAppointmentService();
        PatientService patientService = provider.getPatientService();
        MedicalHistoryService medicalHistoryService = provider.getMedicalHistoryService();

        try {
            MedicalHistory medicalHistory = medicalHistoryService.getByPatientId(patientID);

            Epicrisis epicrisis = epicrisisService.getLastEpicrisisByPatientId(patientID);
            epicrisis.setDefinitiveDiagnosis(definitiveDiagnosis);
            epicrisis.setDischargeDate(dischargeDate);
            epicrisis.setMedicalHistoryId(medicalHistory.getId());
            epicrisisService.update(epicrisis);

            Patient patient = patientService.getPatientById(patientID);
            patient.setAttendingDoctorID(0L);
            patient.setStatusID(2);
            patientService.update(patient);

            List<Appointment> appointmentList = appointmentService.getAllAppointmentBetweenDate(epicrisis.getReceiptDate(),epicrisis.getDischargeDate(),patient.getId());
            appointmentService.updateAppointmentEpirisis(appointmentList,epicrisis.getId());

            session.setAttribute(ATTRIBUTE_URL,GO_TO_MAIN_PAGE);
            session.setAttribute(ATTRIBUTE_INFO_MESSAGE,Arrays.asList(EPICRISIS_ADDED_OK));
            response.sendRedirect(GO_TO_MAIN_PAGE);

        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_ERROR_MESSAGE, Arrays.asList(EPICRISIS_ADDED_ERROR));
            session.setAttribute(ATTRIBUTE_URL,GO_TO_ERROR_PAGE);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }



    }
}
