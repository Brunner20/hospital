package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToMainPage implements Command {

   // public static final Logger logger = LogManager.getLogger(GoToMainPage.class);

    private static final String WRONG_SESSION ="Controller?command=gotoindexpage&message=wrong session";
    private static final String WRONG_AUTH ="Controller?command=gotoindexpage&message=wrong auth";
    private static final String PATH_TO_MAIN = "/WEB-INF/jsp/main.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        if(session == null) {
           // logger.log(Level.ERROR,"session is null");
            response.sendRedirect(WRONG_SESSION);
            return;
        }

        Boolean isAuth = (Boolean) session.getAttribute("auth");

        if (isAuth == null || !isAuth) {
           // logger.log(Level.ERROR,"Auth is null");
            response.sendRedirect(WRONG_AUTH);
            return;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_MAIN);
       // logger.log(Level.INFO,"go to main.jsp");
        requestDispatcher.forward(request, response);

    }

}
