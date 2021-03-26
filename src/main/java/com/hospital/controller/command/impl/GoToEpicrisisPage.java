package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.*;

public class GoToEpicrisisPage implements Command {

    private static final String PATH_TO_EPICRISIS = "/WEB-INF/jsp/epicrisis.jsp";
    private static final String GO_TO_EPICRISIS_PAGE = "Controller?command=gotoepicrisispage";
    private static final String PATIENT_ID = "patient_id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        String role  = (String) session.getAttribute(ATTRIBUTE_ROLE);

        if (isAuth == null || !isAuth || role.equals(ROLE_PATIENT)) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_AUTH);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        session.setAttribute(ATTRIBUTE_URL,GO_TO_EPICRISIS_PAGE);
        request.setAttribute(PATIENT_ID,request.getParameter(PATIENT_ID));
        request.getRequestDispatcher(PATH_TO_EPICRISIS).forward(request, response);
    }
}
