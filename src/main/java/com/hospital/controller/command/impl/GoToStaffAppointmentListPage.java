package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.dto.AppointmentDTO;
import com.hospital.service.AppointmentService;
import com.hospital.service.ServiceProvider;
import com.hospital.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.hospital.controller.command.CommandParameter.*;

public class GoToStaffAppointmentListPage implements Command {

    private static final String GO_TO_APPOINTMENT_STAFF_LIST_PAGE = "Controller?command=gotostaffappointmentlistpage";
    public static final String PATH_TO_APPOINTMENT_LIST_PAGE ="/WEB-INF/jsp/appointment_list_for_staff.jsp";
    private static final String ATTRIBUTE_APPOINTMENT_LIST = "appointment_list";
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
            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_AUTH);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        long visitorId = (long) session.getAttribute(ATTRIBUTE_VISITOR_ID);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        AppointmentService appointmentService = serviceProvider.getAppointmentService();
        List<AppointmentDTO> allAppointments = new ArrayList<>();
        if(session.getAttribute(ATTRIBUTE_ROLE).equals(ROLE_DOCTOR))
        {
            try {
                allAppointments = appointmentService.getAllAppointmentsByStaffId(visitorId);
            } catch (ServiceException e) {
                session.setAttribute(ATTRIBUTE_URL,GO_TO_STAFF_PAGE);
                response.sendRedirect(GO_TO_STAFF_PAGE);
                return;
            }
        }
        session.setAttribute(ATTRIBUTE_URL,GO_TO_APPOINTMENT_STAFF_LIST_PAGE);
        request.setAttribute(ATTRIBUTE_APPOINTMENT_LIST,allAppointments );
        request.getRequestDispatcher(PATH_TO_APPOINTMENT_LIST_PAGE).forward(request, response);
    }
}
