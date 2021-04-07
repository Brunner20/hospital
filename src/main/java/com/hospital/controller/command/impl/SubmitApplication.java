package com.hospital.controller.command.impl;

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

import static com.hospital.controller.command.CommandParameter.*;
import static com.hospital.controller.command.CommandParameter.GO_TO_INDEX_PAGE;

public class SubmitApplication implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        String role  = (String) session.getAttribute(ATTRIBUTE_ROLE);
        if (isAuth == null || !isAuth|| !role.equals(ROLE_PATIENT)  ) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        Long patientId = (Long)session.getAttribute(ATTRIBUTE_VISITOR_ID);
        try {
            PatientService patientService = ServiceProvider.getInstance().getPatientService();
            Patient patient = patientService.getPatientById(patientId);
            patient.setStatusID(3);
            patientService.update(patient);
            request.setAttribute("patient",patient);
            session.setAttribute(ATTRIBUTE_URL,GO_TO_MAIN_PAGE);
            response.sendRedirect(GO_TO_MAIN_PAGE);
        }catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_ERROR_PAGE);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }
    }
}
