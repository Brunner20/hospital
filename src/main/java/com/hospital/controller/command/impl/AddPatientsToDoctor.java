package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.Epicrisis;
import com.hospital.entity.Patient;
import com.hospital.service.EpicrisisService;
import com.hospital.service.PatientService;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

import static com.hospital.controller.command.CommandParameter.*;

public class AddPatientsToDoctor implements Command {

    private static final String GO_TO_STAFF_PAGE = "Controller?command=gotomainstaffpage";
    private static final String PATH_TO_FREE_PATIENTS = "/WEB-INF/jsp/free_patients.jsp";


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


        long selectedPatientsId = Long.parseLong(request.getParameter("free_patient_id"));
        String preliminaryDiagnosis = request.getParameter("preliminaryDiagnosis");
        Long doctorId = (Long)session.getAttribute(ATTRIBUTE_VISITOR_ID);
        Date receiptDate = Date.valueOf(request.getParameter("receiptDate"));
        Epicrisis epicrisis  = new Epicrisis();
        epicrisis.setPatientId(selectedPatientsId);
        epicrisis.setPreliminaryDiagnosis(preliminaryDiagnosis);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        PatientService patientService = serviceProvider.getPatientService();
        EpicrisisService epicrisisService = serviceProvider.getEpicrisisService();

        try {
            epicrisisService.addEpicrisis(epicrisis);

            Patient patient = patientService.getPatientById(selectedPatientsId);
            patient.setAttendingDoctorID(doctorId);
            patient.setReceiptDate(receiptDate);
            patientService.update(patient);

            session.setAttribute(ATTRIBUTE_URL,PATH_TO_FREE_PATIENTS);
            response.sendRedirect(GO_TO_STAFF_PAGE);
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_STAFF_PAGE);
            response.sendRedirect(GO_TO_STAFF_PAGE);
        }
    }
}
