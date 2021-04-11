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
        if(session == null) {
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }
        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        String role  = (String) session.getAttribute(ATTRIBUTE_ROLE);

        if (isAuth == null || !isAuth || role.equals(ROLE_PATIENT)) {
                session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
                response.sendRedirect(GO_TO_INDEX_PAGE);
                return;
        }
        StaffService staffService = ServiceProvider.getInstance().getStaffService();
        PatientService patientService = ServiceProvider.getInstance().getPatientService();
        List<Staff> allStaff;
        Patient patient;
        try {
            patient = patientService.getPatientById(Long.valueOf(request.getParameter(SELECTED_PATIENT)));
            if(request.getParameter(SELECTED_TYPE).equals("3")){
                allStaff = staffService.getAllByType(1L);
            }else{
                allStaff = staffService.getAllByType(2L);
                allStaff.addAll(staffService.getAllByType(1L));
            }
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_URL, GO_TO_ERROR_PAGE);
            response.sendRedirect(GO_TO_ERROR_PAGE);
            return;
        }

        session.setAttribute(ATTRIBUTE_URL, GO_TO_APPOINT_NEXT_PAGE);
        request.setAttribute(SELECTED_TYPE, request.getParameter(SELECTED_TYPE));
        request.setAttribute(ATTRIBUTE_APPOINT_DATE, request.getParameter(ATTRIBUTE_APPOINT_DATE));
        request.setAttribute(SELECTED_PATIENT, patient);
        request.setAttribute(PATIENT_ALL_STAFF, allStaff);
        request.getRequestDispatcher(PATH_TO_APPOINTMENT_NEXT_PAGE).forward(request, response);
    }
}
