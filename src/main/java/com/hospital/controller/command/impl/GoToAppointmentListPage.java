package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.dto.AppointmentDTO;
import com.hospital.service.DocumentationService;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.hospital.controller.command.CommandParameter.*;

public class GoToAppointmentListPage implements Command {

    long visitorId;

    private static final String GO_TO_APPOINTMENT_LIST_PAGE = "Controller?command=gotoappointmentlistpage";
    public static final String PATH_TO_APPOINTMENT_LIST_PAGE ="/WEB-INF/jsp/appointment_list.jsp";
    private static final String ATTRIBUTE_APPOINTMENT_LIST = "appointment_list";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        if(session == null) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }
        session.setAttribute(ATTRIBUTE_URL,GO_TO_APPOINTMENT_LIST_PAGE);

        visitorId = (long) session.getAttribute(ATTRIBUTE_VISITOR_ID);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        DocumentationService documentationService = serviceProvider.getDocumentationService();
        List<AppointmentDTO> allAppointments = new ArrayList<>();
        if(session.getAttribute(ATTRIBUTE_ROLE).equals(ROLE_PATIENT))
        {
            try {
                allAppointments = documentationService.getAllAppointmentsByPatientId(visitorId);
            } catch (ServiceException e) {
                session.setAttribute(ATTRIBUTE_URL,GO_TO_PATIENT_PAGE);
                response.sendRedirect(GO_TO_PATIENT_PAGE);
                return;
            }

        }else {
           // allAppointments = documentationService.getAllAppointmentsByStaffId(visitorId);
        }
        request.setAttribute(ATTRIBUTE_APPOINTMENT_LIST,allAppointments );
        request.getRequestDispatcher(PATH_TO_APPOINTMENT_LIST_PAGE).forward(request, response);
    }
}
