package com.hospital.controller;

import com.hospital.entity.Patient;
import com.hospital.entity.Staff;
import com.hospital.service.PatientService;
import com.hospital.service.ServiceProvider;
import com.hospital.service.StaffService;
import com.hospital.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.*;

@MultipartConfig
public class UploadFileController extends HttpServlet {

    private static final long serialVersionUID = 8428742754L;

    private static final String GO_TO_PROFILE_PAGE = "Controller?command=gotoprofilepage";
    private static final String PATH_TO_ADDITIONAL_INFO_PAGE = "/WEB-INF/jsp/additional_info.jsp";

    public UploadFileController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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



        Part filePart = request.getPart("file");

        if(role.equals(ROLE_PATIENT)) {
            PatientService patientService = ServiceProvider.getInstance().getPatientService();
            try {
                Patient patient = patientService.getPatientById((long) session.getAttribute(ATTRIBUTE_VISITOR_ID));
                patientService.savePictureToPatient(patient,filePart);

            } catch (ServiceException e) {
                session.setAttribute(ATTRIBUTE_URL, GO_TO_PATIENT_PAGE);
                response.sendRedirect(GO_TO_PATIENT_PAGE);
            }
            session.setAttribute(ATTRIBUTE_URL, GO_TO_PROFILE_PAGE);
            response.sendRedirect(GO_TO_PROFILE_PAGE);
        }else {
            StaffService staffService = ServiceProvider.getInstance().getStaffService();
            try {
                Staff staff = staffService.getStaffById((long) session.getAttribute(ATTRIBUTE_VISITOR_ID));
                staffService.savePictureToStaff(staff,filePart);

            } catch (ServiceException e) {
                session.setAttribute(ATTRIBUTE_URL, GO_TO_STAFF_PAGE);
                request.getRequestDispatcher(GO_TO_STAFF_PAGE).forward(request, response);
            }
            session.setAttribute(ATTRIBUTE_URL, GO_TO_PROFILE_PAGE);
            response.sendRedirect(GO_TO_PROFILE_PAGE);
        }

    }
}
