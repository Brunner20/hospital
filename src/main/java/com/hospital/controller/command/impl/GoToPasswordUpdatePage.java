package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.ATTRIBUTE_URL;

public class GoToPasswordUpdatePage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession(true);
        session.setAttribute(ATTRIBUTE_URL,GO_TO_ADMIN);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_MAIN_ADMIN);
        requestDispatcher.forward(request, response);
    }
}
