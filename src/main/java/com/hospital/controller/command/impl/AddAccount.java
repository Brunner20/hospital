package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.RegistrationInfo;
import com.hospital.service.AccountService;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddAccount implements Command {

    private static final String GO_TO_INDEX_PAGE = "Controller?command=gotoindexpage";

    private static final String ATTRIBUTE_INFO_MESSAGE = "informationMessage";
    private static final String REGISTRATION_OK = "registration successful";
    private static final String REGISTRATION_ERROR = "registration unsuccessful";

    private static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    private static final String WRONG_IN_CATCH = "wrong in catch";

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";

    private static final String ATTRIBUTE_URL = "url";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstname;
        String lastname;
        String login;
        String password;

        firstname = request.getParameter(FIRSTNAME);
        lastname = request.getParameter(LASTNAME);
        login = request.getParameter(LOGIN);
        password = request.getParameter(PASSWORD);

        HttpSession session = request.getSession(true);
        session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);

        RegistrationInfo registrationInfo = new RegistrationInfo(firstname,lastname,login,password);

        ServiceProvider provider = ServiceProvider.getInstance();
        AccountService userService = provider.getAccountService();
        try {
           if(!userService.registration(registrationInfo)){
               request.setAttribute(ATTRIBUTE_INFO_MESSAGE,REGISTRATION_OK);
               response.sendRedirect(GO_TO_INDEX_PAGE);
               return;
           }
           request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,REGISTRATION_ERROR);
           response.sendRedirect(GO_TO_INDEX_PAGE);

        } catch (ServiceException e) {
            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_IN_CATCH );
            response.sendRedirect(GO_TO_INDEX_PAGE);
        }
    }
}
