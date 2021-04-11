package com.hospital.controller.command.impl;

import com.hospital.bean.Patient;
import com.hospital.controller.command.Command;
import com.hospital.service.PatientService;
import com.hospital.service.ServiceProvider;
import com.hospital.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

import static com.hospital.controller.command.CommandParameter.*;


public class AddAdditionalInfo implements Command {

    private static final String PATH_TO_ADDITIONAL_INFO_PAGE = "/WEB-INF/jsp/additional_info.jsp";

    private static final String ERROR_DATA = "local.error.data_format";



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if(session == null) {
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        String role  = (String) session.getAttribute(ATTRIBUTE_ROLE);

        if (isAuth == null || !isAuth || !role.equals(ROLE_PATIENT)) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }


        String age = request.getParameter(AGE);
        String firstname = request.getParameter(FIRSTNAME);
        String lastname = request.getParameter(LASTNAME);
        ServiceProvider provider = ServiceProvider.getInstance();
        PatientService patientService = provider.getPatientService();

        try {
                Patient patient = patientService.getPatientById((Long) session.getAttribute(ATTRIBUTE_VISITOR_ID));
                patient.setAge(Integer.parseInt(age));
                patient.setFirstname(firstname);
                patient.setLastname(lastname);
                patientService.update(patient);
                session.setAttribute(ATTRIBUTE_URL,GO_TO_MAIN_PAGE);
                response.sendRedirect(GO_TO_MAIN_PAGE);

            } catch (ServiceException e) {
                request.setAttribute(ATTRIBUTE_ERROR_MESSAGE, Arrays.asList(ERROR_DATA));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_ADDITIONAL_INFO_PAGE);
                requestDispatcher.forward(request, response);
            }

    }
}
