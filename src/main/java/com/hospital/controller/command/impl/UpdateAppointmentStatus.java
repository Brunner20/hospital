package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.AppointmentStatus;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.*;


public class UpdateAppointmentStatus implements Command {

    private static final String ATTRIBUTE_STATUS = "status";
    private static final String ATTRIBUTE_APPOINTMENT_ID = "appointment_id";

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

        if (isAuth == null || !isAuth || role.equals(ROLE_PATIENT)) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_AUTH);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        String appointmentNewStatus = request.getParameter(ATTRIBUTE_STATUS);
        Long appointmentId = Long.parseLong(request.getParameter(ATTRIBUTE_APPOINTMENT_ID));

        try {
            ServiceProvider.getInstance().getAppointmentService()
                    .updateAppointmentStatus(appointmentId, AppointmentStatus.valueOf(appointmentNewStatus.toUpperCase()));
            
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_STAFF_PAGE);
            response.sendRedirect(GO_TO_STAFF_PAGE);
        }
        response.sendRedirect(GO_TO_STAFF_PAGE);

    }
}
