package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.Patient;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;
import com.hospital.service.StaffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToMainStaffPage implements Command {

   // public static final Logger logger = LogManager.getLogger(GoToMainPage.class);

    private static final String GO_TO_INDEX_PAGE = "Controller?command=gotoindexpage";


    private static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    private static final String WRONG_IN_SESSION = "wrong session";
    private static final String WRONG_AUTH ="wrong auth";

    private static final String GO_TO_ERROR_PAGE = "error.jsp";
    private static final String PATH_TO_MAIN = "/WEB-INF/jsp/main_staff.jsp";
    private static final String ATTRIBUTE_AUTH = "auth";
    private static final String ATTRIBUTE_URL = "url";
    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_PATIENT = "patientList";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(true);

        if(session == null) {
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);

        if (isAuth == null || !isAuth) {
           session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
           request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_AUTH);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        StaffService staffService = serviceProvider.getStaffService();

        List<Patient> patients = null;
        try {
            patients = staffService.getAllPatients((Long) session.getAttribute(ATTRIBUTE_ID));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute(ATTRIBUTE_PATIENT,patients);
        session.setAttribute(ATTRIBUTE_URL,PATH_TO_MAIN);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_MAIN);
        requestDispatcher.forward(request, response);


    }

}
