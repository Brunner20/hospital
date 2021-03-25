package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.*;

public class GoToMainPatientPage implements Command {




    private static final String PATH_TO_MAIN_PATIENT ="/WEB-INF/jsp/main_patient.jsp";


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
            session.setAttribute(ATTRIBUTE_URL,GO_TO_PATIENT_PAGE);
            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_AUTH);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_AUTH);
        session.setAttribute(ATTRIBUTE_URL,GO_TO_PATIENT_PAGE);
        request.getRequestDispatcher(PATH_TO_MAIN_PATIENT).forward(request, response);
    }
}
