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

    private static final String REGISTRATION_SUCCESSFUL = "Controller?command=gotoindexpage&message=registration is successful";
    private static final String REGISTRATION_UNSUCCESSFUL = "Controller?command=gotoindexpage&message=registration is unsuccessful";
    private static final String WRONG_IN_CATCH = "Controller?command=gotoindexpage&message=wrong in catch";

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
               response.sendRedirect(REGISTRATION_SUCCESSFUL);
               return;
           }
            response.sendRedirect(REGISTRATION_UNSUCCESSFUL);

        } catch (ServiceException e) {
            response.sendRedirect(WRONG_IN_CATCH);
            e.printStackTrace();
        }
    }
}
