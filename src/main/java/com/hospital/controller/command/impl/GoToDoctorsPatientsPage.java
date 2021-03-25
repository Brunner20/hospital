package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.Patient;
import com.hospital.service.PatientService;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.hospital.controller.command.CommandParameter.*;

public class GoToDoctorsPatientsPage implements Command {


    private static final String PATH_TO_PATIENTS = "/WEB-INF/jsp/patients.jsp";
    private static final String ATTRIBUTE_PATIENT = "patientList";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession(true);

        if(session == null) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        PatientService patientService = serviceProvider.getPatientService();

        List<Patient> patients = null;
        try {
            patients = patientService.getAllPatientsByStaff((Long) session.getAttribute(ATTRIBUTE_VISITOR_ID));
            request.setAttribute(ATTRIBUTE_PATIENT, patients);
            request.getRequestDispatcher(PATH_TO_PATIENTS).forward(request,response);
        }catch (ServiceException e){
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
        }
    }
}
