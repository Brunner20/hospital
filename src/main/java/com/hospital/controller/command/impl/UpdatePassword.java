package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.Patient;
import com.hospital.entity.Staff;
import com.hospital.service.AccountService;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.*;

public class UpdatePassword  implements Command{

    private static final String GO_TO_UPDATE_PASSWORD_PAGE = "Controller?command=gotomainadminpage";

    private static final String OLD_PASSWORD = "old_password";
    private static final String NEW_PASSWORD = "new_password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        if(session == null) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        String role  = (String) session.getAttribute(ATTRIBUTE_ROLE);

        if (isAuth == null || !isAuth ) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_AUTH);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }
        String pageToReturn = GO_TO_INDEX_PAGE;
        String oldPass = request.getParameter(OLD_PASSWORD);
        String newPass = request.getParameter(NEW_PASSWORD);
        ServiceProvider provider = ServiceProvider.getInstance();
        long accountId = 0;
        try {
            if (session.getAttribute(ATTRIBUTE_ROLE).equals(ROLE_PATIENT)) {
                Patient patient = provider.getPatientService().getPatientById((Long) session.getAttribute(ATTRIBUTE_VISITOR_ID));
                accountId = patient.getAccountID();
                pageToReturn = GO_TO_PATIENT_PAGE;
            } else if (session.getAttribute(ATTRIBUTE_ROLE).equals(ROLE_DOCTOR)||session.getAttribute(ATTRIBUTE_ROLE).equals(ROLE_NURSE)) {
                Staff staff = provider.getStaffService().getStaffById((Long) session.getAttribute(ATTRIBUTE_VISITOR_ID));
                accountId = staff.getAccountID();
                pageToReturn = GO_TO_STAFF_PAGE;
            }

            AccountService accountService = provider.getAccountService();
            accountService.updatePassword(accountId,oldPass,newPass);

            session.setAttribute(ATTRIBUTE_URL,pageToReturn);
            response.sendRedirect(pageToReturn);
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_UPDATE_PASSWORD_PAGE);
            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_AUTH);
            response.sendRedirect(GO_TO_UPDATE_PASSWORD_PAGE);
        }


    }
}
