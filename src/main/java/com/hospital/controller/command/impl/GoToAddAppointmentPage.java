package com.hospital.controller.command.impl;

import com.hospital.bean.AppointmentType;
import com.hospital.bean.Patient;
import com.hospital.controller.command.Command;
import com.hospital.service.PatientService;
import com.hospital.service.ServiceProvider;
import com.hospital.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hospital.controller.command.CommandParameter.*;

public class GoToAddAppointmentPage implements Command {


    private static final String ATTRIBUTE_APPOINTMENT_TYPES = "types";
    private static final String ATTRIBUTE_PATIENTS = "allPatients";
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
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }


        PatientService patientService = ServiceProvider.getInstance().getPatientService();
        List<Patient> patients = new ArrayList<>();
        try {
           patients = patientService.getAll();
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_URL, GO_TO_ERROR_PAGE);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }

        session.setAttribute(ATTRIBUTE_URL,GO_TO_APPOINT_PAGE);
        request.setAttribute(ATTRIBUTE_APPOINTMENT_TYPES, Arrays.asList(AppointmentType.values()));
        request.setAttribute(ATTRIBUTE_PATIENTS, patients);
        request.getRequestDispatcher(PATH_TO_APPOINTMENT).forward(request, response);
    }
}
