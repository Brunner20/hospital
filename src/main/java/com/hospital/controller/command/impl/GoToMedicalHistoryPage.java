package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.dto.EpicrisisDTO;
import com.hospital.service.AppointmentService;
import com.hospital.service.EpicrisisService;
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

public class GoToMedicalHistoryPage implements Command {

    private static final String PATH_TO_HISTORY = "/WEB-INF/jsp/medical_history.jsp";
    private static final String GO_TO_HISTORY = "Controller?command=gotomedicalhistorypage";

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

        if (isAuth == null || !isAuth) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            request.setAttribute(ATTRIBUTE_ERROR_MESSAGE,WRONG_AUTH);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        Long patientId;
        if(!session.getAttribute(ATTRIBUTE_ROLE).equals(ROLE_PATIENT))
        {
            patientId = Long.valueOf(request.getParameter("patientId"));
        }else{
            patientId = (Long) session.getAttribute(ATTRIBUTE_VISITOR_ID);
        }
        ServiceProvider provider = ServiceProvider.getInstance();
        EpicrisisService epicrisisService = provider.getEpicrisisService();
        AppointmentService appointmentService = provider.getAppointmentService();
        List<EpicrisisDTO> epicrisisList = new ArrayList<>();
        try {
            epicrisisList = epicrisisService.getEpicrisisByPatientId(patientId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        session.setAttribute(ATTRIBUTE_URL,GO_TO_HISTORY);
        request.setAttribute("medicalHistory",epicrisisList);
        request.getRequestDispatcher(PATH_TO_HISTORY).forward(request,response);
    }
}
