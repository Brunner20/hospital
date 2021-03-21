package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.service.PatientService;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;
import com.hospital.service.StaffService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AddPatientsToDoctor implements Command {
    private static final String GO_TO_INDEX_PAGE = "Controller?command=gotoindexpage";
    private static final String GO_TO_STAFF_PAGE = "Controller?command=gotomainstaffpage";
    private static final String PATH_TO_FREE_PATIENTS = "/WEB-INF/jsp/free_patients.jsp";
    private static final String ATTRIBUTE_URL = "url";
    private static final String ATTRIBUTE_ID = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        if(session == null) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        List<String> selectedPatientsIds = Arrays.asList(request.getParameterValues("selected"));
        Long doctorId = (Long)session.getAttribute(ATTRIBUTE_ID);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        PatientService patientService = serviceProvider.getPatientService();
        try {
            patientService.updateDoctor(selectedPatientsIds,doctorId);
            session.setAttribute(ATTRIBUTE_URL,PATH_TO_FREE_PATIENTS);
            response.sendRedirect(GO_TO_STAFF_PAGE);
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
        }
    }
}
