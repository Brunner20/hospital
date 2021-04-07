package com.hospital.controller.command.impl;

import com.hospital.bean.Epicrisis;
import com.hospital.bean.Patient;
import com.hospital.controller.command.Command;
import com.hospital.service.EpicrisisService;
import com.hospital.service.PatientService;
import com.hospital.service.ServiceProvider;
import com.hospital.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import static com.hospital.controller.command.CommandParameter.*;

public class AddPatientsToDoctor implements Command {


    private static final String PATH_TO_FREE_PATIENTS = "/WEB-INF/jsp/free_patients.jsp";
    private static final String PATIENT_ADDED_OK = "local.info.patient_added";
    private static final String PATIENT_ADDED_ERROR = "local.error.patient_not_added";


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


        long selectedPatientsId = Long.parseLong(request.getParameter("free_patient_id"));
        String preliminaryDiagnosis = request.getParameter("preliminaryDiagnosis");
        Long doctorId = (Long)session.getAttribute(ATTRIBUTE_VISITOR_ID);
        Date receiptDate = Date.valueOf(request.getParameter("receiptDate"));

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        PatientService patientService = serviceProvider.getPatientService();
        EpicrisisService epicrisisService = serviceProvider.getEpicrisisService();

        try {
            Epicrisis epicrisis  = new Epicrisis();
            epicrisis.setPatientId(selectedPatientsId);
            epicrisis.setPreliminaryDiagnosis(preliminaryDiagnosis);
            epicrisis.setReceiptDate(receiptDate);
            epicrisisService.addEpicrisis(epicrisis);

            Patient patient = patientService.getPatientById(selectedPatientsId);
            patient.setAttendingDoctorID(doctorId);
            patient.setStatusID(1);
            patientService.update(patient);

            session.setAttribute(ATTRIBUTE_INFO_MESSAGE, Arrays.asList(PATIENT_ADDED_OK));
            session.setAttribute(ATTRIBUTE_URL,GO_TO_MAIN_PAGE);
            response.sendRedirect(GO_TO_MAIN_PAGE);
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_ERROR_MESSAGE,Arrays.asList(PATIENT_ADDED_ERROR));
            session.setAttribute(ATTRIBUTE_URL,GO_TO_MAIN_PAGE);
            response.sendRedirect(GO_TO_MAIN_PAGE);
        }
    }
}
