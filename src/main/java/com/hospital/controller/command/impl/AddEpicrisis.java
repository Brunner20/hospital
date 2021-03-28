package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.Appointment;
import com.hospital.entity.Epicrisis;
import com.hospital.entity.MedicalHistory;
import com.hospital.entity.Patient;
import com.hospital.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static com.hospital.controller.command.CommandParameter.*;

public class AddEpicrisis implements Command {


    private static final String DISCHARGE_DATE = "dischargeDate";
    private static final String DEFINITIVE_DIAGNOSIS = "definitiveDiagnosis";
    private static final String PATIENT_ID = "patient_id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        if(session == null) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        String role  = (String) session.getAttribute(ATTRIBUTE_ROLE);

        if (isAuth == null || !isAuth || role.equals(ROLE_PATIENT)) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_AUTH);
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

            List<Appointment> appointmentList = appointmentService.getAllAppointmentBetweenDate(epicrisis.getReceiptDate(),epicrisis.getDischargeDate());
            appointmentService.updateAppointmentEpirisis(appointmentList,epicrisis.getId());



            session.setAttribute(ATTRIBUTE_URL,GO_TO_STAFF_PAGE);
            response.sendRedirect(GO_TO_STAFF_PAGE);

        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_STAFF_PAGE);
            response.sendRedirect(GO_TO_STAFF_PAGE);
        }



    }
}
