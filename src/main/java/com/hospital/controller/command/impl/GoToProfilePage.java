package com.hospital.controller.command.impl;

import com.hospital.bean.Patient;
import com.hospital.bean.Staff;
import com.hospital.controller.command.Command;
import com.hospital.service.PatientService;
import com.hospital.service.ServiceProvider;
import com.hospital.service.StaffService;
import com.hospital.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.*;

public class GoToProfilePage implements Command {


    private static final String GO_TO_PROFILE_PAGE = "Controller?command=gotoprofilepage";
    private static final String PATH_TO_PROFILE = "/WEB-INF/jsp/profile.jsp";



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if(session == null) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        if (isAuth == null || !isAuth) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        Long id = (Long) session.getAttribute(ATTRIBUTE_VISITOR_ID);
        String role = (String)session.getAttribute(ATTRIBUTE_ROLE);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        StaffService staffService = serviceProvider.getStaffService();
        PatientService patientService = serviceProvider.getPatientService();
        try {
            if (role.contains(ROLE_DOCTOR) || role.contains(ROLE_NURSE)) {

                Staff staff = staffService.getStaffById(id);
                request.setAttribute("staff",staff);
            } else {
                Patient patient = patientService.getPatientById(id);
                Staff attendingDoctor = staffService.getStaffById(patient.getAttendingDoctorID());
                request.setAttribute("patient",patient);
                request.setAttribute("attendingDoctor",attendingDoctor);
            }
            session.setAttribute(ATTRIBUTE_URL,GO_TO_PROFILE_PAGE);
            request.getRequestDispatcher(PATH_TO_PROFILE).forward(request, response);
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            }

    }
}
