package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToMainPatientPage implements Command {

    private static final String GO_TO_INDEX_PAGE = "Controller?command=gotoindexpage";
    private static final String GO_TO_PATIENT_PAGE = "Controller?command=gotomainpatientpage";

    private static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    private static final String WRONG_AUTH ="wrong auth";

    private static final String PATH_TO_MAIN_PATIENT ="/WEB-INF/jsp/main_patient.jsp";
    private static final String ATTRIBUTE_URL = "url";
    private static final String ATTRIBUTE_AUTH = "auth";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        if(session == null) {
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);

        if (isAuth == null || !isAuth) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_PATIENT_PAGE);
            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_AUTH);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_AUTH);
        session.setAttribute(ATTRIBUTE_URL,GO_TO_PATIENT_PAGE);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_MAIN_PATIENT);
        requestDispatcher.forward(request, response);
    }
}
