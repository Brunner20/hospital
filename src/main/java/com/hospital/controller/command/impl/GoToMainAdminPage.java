package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.*;

public class GoToMainAdminPage implements Command{

    private static final String PATH_TO_MAIN_ADMIN = "/WEB-INF/jsp/main_admin.jsp";
    private static final String GO_TO_ADMIN = "Controller?command=gotomainadminpage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String role  = (String) session.getAttribute(ATTRIBUTE_ROLE);
        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);

        if (isAuth == null || !isAuth || !role.equals(ROLE_ADMIN)) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        session.setAttribute(ATTRIBUTE_URL,GO_TO_ADMIN);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_MAIN_ADMIN);
        requestDispatcher.forward(request, response);

    }
}
