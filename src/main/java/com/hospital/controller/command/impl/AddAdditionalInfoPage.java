package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.service.PatientService;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.*;


public class AddAdditionalInfoPage implements Command {
    private static final String PIC = "pic_path";
    private static final String AGE = "age";

    private static final String GO_TO_PATIENT_PAGE = "Controller?command=gotomainpatientpage";
    private static final String PATH_TO_MAIN_PATIENT = "/WEB-INF/jsp/main_patient.jsp";
    private static final String PATH_TO_ADDITIONAL_INFO_PAGE = "/WEB-INF/jsp/additional_info.jsp";

    private static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    private static final String WRONG_AGE = "wrong age";


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

        if (isAuth == null || !isAuth || !role.equals(ROLE_PATIENT)) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_AUTH);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }


        String age;

        age = request.getParameter(AGE);


        ServiceProvider provider = ServiceProvider.getInstance();
        PatientService patientService = provider.getPatientService();


        try {
                patientService.updateAge((Long) session.getAttribute(ATTRIBUTE_VISITOR_ID),age);
                session.setAttribute(ATTRIBUTE_URL,PATH_TO_MAIN_PATIENT);
                response.sendRedirect(GO_TO_PATIENT_PAGE);

            } catch (ServiceException e) {
                request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_AGE);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_ADDITIONAL_INFO_PAGE);
                requestDispatcher.forward(request, response);
            }

    }
}
