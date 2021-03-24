package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;
import com.hospital.entity.Appointment;
import com.hospital.entity.AppointmentInfo;
import com.hospital.entity.AppointmentStatus;
import com.hospital.entity.AppointmentType;
import com.hospital.service.DocumentationService;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

import static com.hospital.controller.command.CommandParameter.*;

public class AddAppointment implements Command {

    private Date appointmentDate;
    private Date dateOfCompletion;
    private AppointmentType type;
    private String information;
    private Long patientId;
    private Long execStaffId;



    private static final String ATTRIBUTE_APPOINT_DATE = "dateOfAppointment";
    private static final String ATTRIBUTE_COMPLETION_DATE = "dateOfCompletion";
    private static final String INFORMATION = "info";
    private static final String SELECTED_TYPE = "select_type";
    private static final String SELECTED_PATIENT = "select_patient_id";
    private static final String SELECTED_EXEC_STAFF_ = "select_staff_id";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession(true);
        if(session == null) {
            session.setAttribute(ATTRIBUTE_URL, GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        appointmentDate = Date.valueOf(request.getParameter(ATTRIBUTE_APPOINT_DATE));
        type = AppointmentType.getAppointmentTypeById(Integer.parseInt(request.getParameter(SELECTED_TYPE)));
        information = request.getParameter(INFORMATION);
        patientId = Long.valueOf(request.getParameter(SELECTED_PATIENT));
        execStaffId = Long.valueOf(request.getParameter(SELECTED_EXEC_STAFF_));
        if(!request.getParameter(SELECTED_TYPE).equals("1"))
        {
            dateOfCompletion = Date.valueOf(request.getParameter(ATTRIBUTE_COMPLETION_DATE));
        }

        DocumentationService docService = ServiceProvider.getInstance().getDocumentationService();
        AppointmentInfo appointmentInfo = null;
        try {
             appointmentInfo = docService.getAppointmentInfo(information,type);
        } catch (ServiceException e) {
            request.setAttribute("error"," not found");
            session.setAttribute(ATTRIBUTE_URL, GO_TO_APPOINT_PAGE);
            response.sendRedirect(GO_TO_APPOINT_PAGE);
        }

        Appointment appointment = new Appointment();
        appointment.setDateOfAppointment(appointmentDate);
        appointment.setInfoId(appointmentInfo.getId());
        appointment.setExecuteStaffId(execStaffId);
        appointment.setPatientId(patientId);
        appointment.setAppointingDoctorId((Long)session.getAttribute(ATTRIBUTE_VISITOR_ID));
        appointment.setStatus(AppointmentStatus.APPOINTED);
        if(dateOfCompletion!=null){
            appointment.setDateOfCompletion(dateOfCompletion);
        }

        DocumentationService documentationService = ServiceProvider.getInstance().getDocumentationService();
        try {
            documentationService.addAppointment(appointment);
        } catch (ServiceException e) {
            request.setAttribute("error","");
            session.setAttribute(ATTRIBUTE_URL, GO_TO_ADD_APPOINTMENT_PAGE);
            response.sendRedirect(GO_TO_ADD_APPOINTMENT_PAGE);
        }

        request.getRequestDispatcher(GO_TO_STAFF_PAGE).forward(request,response);

    }
}
