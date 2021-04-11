package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.ATTRIBUTE_URL;


/**
 * Command to go to registration page
 */
public class Registration implements Command {

    private static final String GO_TO_REG_PAGE ="/WEB-INF/jsp/registration.jsp";
    private static final String GO_TO_REGISTRATION_PAGE = "Controller?command=registration";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        session.setAttribute(ATTRIBUTE_URL, GO_TO_REGISTRATION_PAGE);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_REG_PAGE);
        requestDispatcher.forward(request, response);
    }
}
