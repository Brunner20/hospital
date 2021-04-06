package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.Account;
import com.hospital.entity.Patient;
import com.hospital.entity.Staff;
import com.hospital.service.AccountService;
import com.hospital.service.ServiceProvider;
import com.hospital.service.exception.DataFormatServiceException;
import com.hospital.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

import static com.hospital.controller.command.CommandParameter.*;


public class Login implements Command {

    private static final String GO_TO_MAIN_ADMIN_PAGE ="Controller?command=gotomainadminpage";
    private static final String PATH_TO_ADDITIONAL_INFO_PAGE = "/WEB-INF/jsp/additional_info.jsp";


    private static final String WRONG_LOGIN_OR_PASSWORD = "local.message.login";
    private static final String ERROR_DATA = "local.error.data_format";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        ServiceProvider provider = ServiceProvider.getInstance();
        AccountService accountService = provider.getAccountService();

        HttpSession session = request.getSession(true);
        Account account;
        try {

            account = accountService.authorization(login, password);

            if (account == null) {
                session.setAttribute(ATTRIBUTE_ERROR_MESSAGE, Arrays.asList(WRONG_LOGIN_OR_PASSWORD));
                response.sendRedirect(GO_TO_INDEX_PAGE);
                return;
            }


            session.setAttribute(ATTRIBUTE_AUTH, true);
            if(account.getRoleId()==2) {
                Staff staff = ServiceProvider.getInstance().getStaffService().getStaffByAccount(account.getId());
                session.setAttribute(ATTRIBUTE_URL, GO_TO_MAIN_PAGE);
                if(staff.getStaffTypeID()==1) {
                    session.setAttribute(ATTRIBUTE_ROLE,ROLE_DOCTOR);
                }
                else {
                    session.setAttribute(ATTRIBUTE_ROLE,ROLE_NURSE);
                }
                session.setAttribute(ATTRIBUTE_VISITOR_ID,(staff.getId()));
                response.sendRedirect(GO_TO_MAIN_PAGE);
            }
            else if (account.getRoleId()==3) {
                Patient patient = ServiceProvider.getInstance().getPatientService().getPatientByAccount(account.getId());
                session.setAttribute(ATTRIBUTE_ROLE,ROLE_PATIENT);
                session.setAttribute(ATTRIBUTE_VISITOR_ID,(patient.getId()));
                if(patient.getAge()==0) {
                    session.setAttribute(ATTRIBUTE_URL,PATH_TO_ADDITIONAL_INFO_PAGE);
                    request.getRequestDispatcher(PATH_TO_ADDITIONAL_INFO_PAGE).forward(request, response);
                }else {
                    session.setAttribute(ATTRIBUTE_URL,GO_TO_MAIN_PAGE);
                    response.sendRedirect(GO_TO_MAIN_PAGE);
                }
            }else if (account.getRoleId()==1) {

                session.setAttribute(ATTRIBUTE_ROLE,ROLE_ADMIN);
                session.setAttribute(ATTRIBUTE_URL, GO_TO_MAIN_ADMIN_PAGE);
                response.sendRedirect(GO_TO_MAIN_ADMIN_PAGE);
            }
        }catch (DataFormatServiceException e) {
            session.setAttribute(ATTRIBUTE_ERROR_MESSAGE,Arrays.asList(ERROR_DATA));
            response.sendRedirect(GO_TO_INDEX_PAGE);
        } catch (ServiceException e) {
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }
    }
}
