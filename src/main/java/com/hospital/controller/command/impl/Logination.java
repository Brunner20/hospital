package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.Account;
import com.hospital.entity.Visitor;
import com.hospital.service.AccountService;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



public class Logination implements Command {

    public static final Logger logger = LogManager.getLogger(Logination.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login;
        String password;

        login = request.getParameter("login");
        password = request.getParameter("password");

        ServiceProvider provider = ServiceProvider.getInstance();
        AccountService userService = provider.getAccountService();

        Visitor user = null;
        RequestDispatcher requestDispatcher = null;
        try {
            //TODO: проверка на тип пользователя
            user = userService.authorization(login, password);

            if (user == null) {
                logger.log(Level.WARN,"login or password invalid");
                response.sendRedirect("Controller?command=gotoindexpage&message=wrong login  or password");
                return;
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("auth", true);
            logger.log(Level.INFO,"logination is successful");
            response.sendRedirect("Controller?command=gotomainpage");

        } catch (ServiceException e) {
            logger.log(Level.ERROR,"wring in catch:"+e.getMessage());
            response.sendRedirect("Controller?command=gotoindexpage&message=wrong in catch");
        }
    }
}
