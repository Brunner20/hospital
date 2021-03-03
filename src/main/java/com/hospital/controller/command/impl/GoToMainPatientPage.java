package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToMainPatientPage implements Command {

    private static final String WRONG_SESSION ="Controller?command=gotoindexpage&message=wrong session";
    private static final String WRONG_AUTH ="Controller?command=gotoindexpage&message=wrong auth";
    private static final String PATH_TO_MAIN_PATIENT ="/WEB-INF/jsp/main_patient.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if(session == null) {
            response.sendRedirect(WRONG_SESSION);
            return;
        }

        Boolean isAuth = (Boolean) session.getAttribute("auth");

        if (isAuth == null || !isAuth) {
            response.sendRedirect(WRONG_AUTH);
            return;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_MAIN_PATIENT);
        requestDispatcher.forward(request, response);
    }
}
