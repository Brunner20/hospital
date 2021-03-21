package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToIndexPage implements Command {

    public static final Logger logger = LogManager.getLogger(GoToIndexPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main_index.jsp");
        requestDispatcher.forward(request, response);

    }
}
