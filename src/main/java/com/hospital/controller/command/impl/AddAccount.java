package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.RegistrationInfo;
import com.hospital.service.AccountService;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAccount implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstname;
        String lastname;
        String login;
        String password;

        firstname = request.getParameter("firstname");
        lastname = request.getParameter("lastname");
        login = request.getParameter("login");
        password = request.getParameter("password");

        RegistrationInfo registrationInfo = new RegistrationInfo(firstname,lastname,login,password);

        ServiceProvider provider = ServiceProvider.getInstance();
        AccountService userService = provider.getAccountService();
        try {
           if(!userService.registration(registrationInfo)){
               response.sendRedirect("Controller?command=gotoindexpage&message=registration is successful");
               return;
           }
            response.sendRedirect("Controller?command=gotoindexpage&message=registration is unsuccessful");

        } catch (ServiceException e) {
            response.sendRedirect("Controller?command=gotoindexpage&message=wrong in catch");
            e.printStackTrace();
        }
    }
}
