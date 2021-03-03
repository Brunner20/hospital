package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.Staff;
import com.hospital.entity.Visitor;
import com.hospital.service.AccountService;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



public class Logination implements Command {

   // public static final Logger logger = LogManager.getLogger(Logination.class);

    private static final String GO_TO_INDEX_PAGE_WROGN ="Controller?command=gotoindexpage&message=wrong login  or password";
    private static final String GO_TO_MAIN_PAGE ="Controller?command=gotomainpage";
    private static final String GO_TO_MAIN_PATIENT_PAGE ="Controller?command=gotomainpatientpage";
    private static final String GO_TO_INDEX_PAGE_WROGN_IN_CATCH ="Controller?command=gotoindexpage&message=wrong in catch";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login;
        String password;

        login = request.getParameter("login");
        password = request.getParameter("password");

        ServiceProvider provider = ServiceProvider.getInstance();
        AccountService userService = provider.getAccountService();

        Visitor visitor = null;
        RequestDispatcher requestDispatcher = null;
        try {

            visitor = userService.authorization(login, password);

            if (visitor == null) {
               // logger.log(Level.WARN,"login or password invalid");
                response.sendRedirect(GO_TO_INDEX_PAGE_WROGN);
                return;
            }


            HttpSession session = request.getSession(true);
            session.setAttribute("auth", true);
           // logger.log(Level.INFO,"logination is successful");

            if(visitor instanceof Staff)
            {response.sendRedirect(GO_TO_MAIN_PAGE);
            }
            else
                response.sendRedirect(GO_TO_MAIN_PATIENT_PAGE);

        } catch (ServiceException e) {
           // logger.log(Level.ERROR,"wring in catch:"+e.getMessage());
            response.sendRedirect(GO_TO_INDEX_PAGE_WROGN_IN_CATCH);
        }
    }
}
