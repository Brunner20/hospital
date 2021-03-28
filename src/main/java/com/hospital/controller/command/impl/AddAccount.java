package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.*;
import com.hospital.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.*;

public class AddAccount implements Command {



    private static final String ATTRIBUTE_INFO_MESSAGE = "informationMessage";
    private static final String REGISTRATION_OK = "registration successful";
    private static final String REGISTRATION_ERROR = "registration unsuccessful";
    private static final String GO_TO_ADMIN = "Controller?command=gotomainadminpage";
    private static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    private static final String WRONG_IN_CATCH = "login already exist";

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String STAFF_TYPE = "staff_type";

    private static final String ATTRIBUTE_URL = "url";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstname;
        String lastname;
        String login;
        String password;
        long roleId;
        long staffType = 0;

        firstname = request.getParameter(FIRSTNAME);
        lastname = request.getParameter(LASTNAME);
        login = request.getParameter(LOGIN);
        password = request.getParameter(PASSWORD);

        HttpSession session = request.getSession(true);



        if(session.getAttribute(ATTRIBUTE_ROLE).equals(ROLE_ADMIN))
        {
            session.setAttribute(ATTRIBUTE_URL, GO_TO_ADMIN);
            staffType = Long.parseLong(request.getParameter(STAFF_TYPE));
            roleId = 2;

        } else {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            roleId = 3;
        }

        RegistrationInfo registrationInfo = new RegistrationInfo(firstname,lastname,login,password,roleId);
        ServiceProvider provider = ServiceProvider.getInstance();
        AccountService userService = provider.getAccountService();
        MedicalHistoryService medicalHistoryService = provider.getMedicalHistoryService();
        StaffService staffService = provider.getStaffService();
        try {
           if(!userService.registration(registrationInfo)){
               Visitor visitor =userService.authorization(login,password);
               if(visitor instanceof Patient)
               {
                   Patient patient = (Patient) visitor;
                   MedicalHistory medicalHistory = new MedicalHistory();
                   medicalHistory.setPatientId(patient.getId());
                   medicalHistoryService.add(medicalHistory);
               }else if(visitor instanceof Staff){
                   Staff staff = (Staff) visitor;
                   staff.setStaffTypeID(staffType);
                   staffService.update(staff);
               }
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
