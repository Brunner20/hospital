package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.AppointmentType;
import com.hospital.entity.Patient;
import com.hospital.service.PatientService;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
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
        session.setAttribute(ATTRIBUTE_URL,GO_TO_APPOINT_PAGE);

        PatientService patientService = ServiceProvider.getInstance().getPatientService();
        List<Patient> patients = new ArrayList<>();
        try {
           patients = patientService.getAll();
        } catch (ServiceException e) {
            request.setAttribute("error","patient not found");
            session.setAttribute(ATTRIBUTE_URL, GO_TO_STAFF_PAGE);
            response.sendRedirect(GO_TO_STAFF_PAGE);
        }

        request.setAttribute(ATTRIBUTE_APPOINTMENT_TYPES, Arrays.asList(AppointmentType.values()));
        request.setAttribute(ATTRIBUTE_PATIENTS, patients);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_APPOINTMENT);
        requestDispatcher.forward(request, response);
    }
}
