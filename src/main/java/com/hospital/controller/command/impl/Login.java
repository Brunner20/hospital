package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.Patient;
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


public class Login implements Command {


    private static final String GO_TO_INDEX_PAGE ="Controller?command=gotoindexpage";
    private static final String GO_TO_MAIN_STAFF_PAGE ="Controller?command=gotomainstaffpage";
    private static final String GO_TO_MAIN_PATIENT_PAGE ="Controller?command=gotomainpatientpage";
    private static final String PATH_TO_ADDITIONAL_INFO_PAGE = "/WEB-INF/jsp/additional_info.jsp";

    private static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    private static final String WRONG_IN_CATCH = "wrong in catch";
    private static final String WRONG_LOGIN_OR_PASSWORD = "wrong login  or password";

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private static final String ATTRIBUTE_AUTH = "auth";
    private static final String ATTRIBUTE_URL = "url";
    private static final String ATTRIBUTE_ROLE = "role";
    private static final String ATTRIBUTE_VISITOR_ID = "id";



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login;
        String password;

        login = request.getParameter(LOGIN);
        password = request.getParameter(PASSWORD);

        ServiceProvider provider = ServiceProvider.getInstance();
        AccountService userService = provider.getAccountService();

        Visitor visitor;
        try {

            visitor = userService.authorization(login, password);

            if (visitor == null) {
                request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_LOGIN_OR_PASSWORD);
                response.sendRedirect(GO_TO_INDEX_PAGE);
                return;
            }


            HttpSession session = request.getSession(true);
            session.setAttribute(ATTRIBUTE_AUTH, true);

            if(visitor instanceof Staff)
            {
                Staff staff = (Staff) visitor;
                session.setAttribute(ATTRIBUTE_URL, GO_TO_MAIN_STAFF_PAGE);
                if(staff.getStaffTypeID()==1)
                session.setAttribute(ATTRIBUTE_ROLE,"doctor");
                else
                    session.setAttribute(ATTRIBUTE_ROLE,"nurse");
                session.setAttribute(ATTRIBUTE_VISITOR_ID,((Staff) visitor).getId());
                response.sendRedirect(GO_TO_MAIN_STAFF_PAGE);
            }
            else if (visitor instanceof Patient)
            {
                session.setAttribute(ATTRIBUTE_ROLE,"patient");
                session.setAttribute(ATTRIBUTE_VISITOR_ID,((Patient)visitor).getId());
                Patient patient = (Patient) visitor;
                if(patient.getAge()==0)
                {
                    session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_ADDITIONAL_INFO_PAGE);
                    requestDispatcher.forward(request, response);
                }else {
                    session.setAttribute(ATTRIBUTE_URL,GO_TO_MAIN_PATIENT_PAGE);
                    response.sendRedirect(GO_TO_MAIN_PATIENT_PAGE);
                }

            }

        } catch (ServiceException e) {

            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_IN_CATCH);
            response.sendRedirect(GO_TO_INDEX_PAGE);
        }
    }
}
