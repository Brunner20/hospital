package com.hospital.controller;

import com.hospital.entity.Patient;
import com.hospital.entity.Staff;
import com.hospital.service.PatientService;
import com.hospital.service.ServiceException;
import com.hospital.service.ServiceProvider;
import com.hospital.service.StaffService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import static com.hospital.controller.command.CommandParameter.*;

@MultipartConfig
public class UploadFileController extends HttpServlet {

    private static final long serialVersionUID = 8428742754L;
    private static final String PATH_TO_STORAGE = ResourceBundle.getBundle("application").getString("application.path_to_storage_in_server");
    private static final String PATH_TO_STORAGE_IN_DB = ResourceBundle.getBundle("application").getString("application.path_to_storage_in_db");
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


        String age =request.getParameter("age");
        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        File uploads = new File(PATH_TO_STORAGE);
        File file = new File(uploads, fileName);

        try ( InputStream fileContent = filePart.getInputStream();) {
            Files.copy(fileContent, file.toPath());
        }catch (IOException e ){
            session.setAttribute(ATTRIBUTE_URL,PATH_TO_ADDITIONAL_INFO_PAGE);
            request.getRequestDispatcher(PATH_TO_ADDITIONAL_INFO_PAGE).forward(request, response);
            return;
        }
        if(session.getAttribute(ATTRIBUTE_ROLE).equals(ROLE_PATIENT)) {
            PatientService patientService = ServiceProvider.getInstance().getPatientService();
            try {
                Patient patient = patientService.getPatientById((long) session.getAttribute(ATTRIBUTE_VISITOR_ID));
                patient.setAge(Integer.parseInt(age));
                patient.setPatientPic(PATH_TO_STORAGE_IN_DB + fileName);
                patientService.update(patient);

            } catch (ServiceException e) {
                session.setAttribute(ATTRIBUTE_URL, PATH_TO_ADDITIONAL_INFO_PAGE);
                request.getRequestDispatcher(PATH_TO_ADDITIONAL_INFO_PAGE).forward(request, response);
            }
            session.setAttribute(ATTRIBUTE_URL, GO_TO_PATIENT_PAGE);
            response.sendRedirect(GO_TO_PATIENT_PAGE);
        }else {
            StaffService staffService = ServiceProvider.getInstance().getStaffService();

            Staff staff = null;

            try {
                staff = staffService.getStaffById((long) session.getAttribute(ATTRIBUTE_VISITOR_ID));
                staff.setPicture(PATH_TO_STORAGE_IN_DB + fileName);
               // staffService.update(staff);

            } catch (ServiceException e) {
                session.setAttribute(ATTRIBUTE_URL, GO_TO_STAFF_PAGE);
                request.getRequestDispatcher(GO_TO_STAFF_PAGE).forward(request, response);
            }
            session.setAttribute(ATTRIBUTE_URL, GO_TO_STAFF_PAGE);
            response.sendRedirect(GO_TO_STAFF_PAGE);
        }

    }
}
