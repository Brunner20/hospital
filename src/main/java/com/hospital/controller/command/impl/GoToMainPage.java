package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToMainPage implements Command {

    public static final Logger logger = LogManager.getLogger(GoToMainPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        if(session == null) {
            logger.log(Level.ERROR,"session is null");
            response.sendRedirect("Controller?command=gotoindexpage&message=wrong session");
            return;
        }

        Boolean isAuth = (Boolean) session.getAttribute("auth");

        if (isAuth == null || !isAuth) {
            logger.log(Level.ERROR,"Auth is null");
            response.sendRedirect("Controller?command=gotoindexpage&message=wrong auth");
            return;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        logger.log(Level.INFO,"go to main.jsp");
        requestDispatcher.forward(request, response);

    }

}
