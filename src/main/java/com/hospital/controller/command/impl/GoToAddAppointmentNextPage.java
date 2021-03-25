package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.Staff;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;
import com.hospital.service.StaffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.hospital.controller.command.CommandParameter.*;

public class GoToAddAppointmentNextPage implements Command {


    private static final String ATTRIBUTE_APPOINT_DATE = "dateOfAppointment";
    private static final String SELECTED_TYPE = "select_type";
    private static final String SELECTED_PATIENT = "select_patient_id";
    private static final String PATIENT_ALL_STAFF = "allStaff";


        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(true);
            session.setAttribute(ATTRIBUTE_URL, GO_TO_APPOINT_NEXT_PAGE);

            if(session == null) {
                session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
                response.sendRedirect(GO_TO_INDEX_PAGE);
                return;
            }


            StaffService staffService = ServiceProvider.getInstance().getStaffService();
            List<Staff> allStaff = new ArrayList<>();
            try {
                allStaff = staffService.getAll();
            } catch (ServiceException e) {
                request.setAttribute("error","patient not found");
                session.setAttribute(ATTRIBUTE_URL, GO_TO_APPOINT_PAGE);
                response.sendRedirect(GO_TO_APPOINT_PAGE);
            }

            request.setAttribute(SELECTED_TYPE, request.getParameter(SELECTED_TYPE));
            request.setAttribute(ATTRIBUTE_APPOINT_DATE, request.getParameter(ATTRIBUTE_APPOINT_DATE));
            request.setAttribute(SELECTED_PATIENT, request.getParameter(SELECTED_PATIENT));
            request.setAttribute(PATIENT_ALL_STAFF, allStaff);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_APPOINTMENT_NEXT_PAGE);
            requestDispatcher.forward(request, response);
        }
}
