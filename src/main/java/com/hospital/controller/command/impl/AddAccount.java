package com.hospital.controller.command.impl;

import com.hospital.controller.builder.UserInfoBuilder;
import com.hospital.controller.command.Command;
import com.hospital.entity.*;
import com.hospital.service.*;
import com.hospital.service.exception.DataFormatServiceException;
import com.hospital.service.exception.LoginIsBusyServiceException;
import com.hospital.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

import static com.hospital.controller.command.CommandParameter.*;

public class AddAccount implements Command {


    private static final String GO_TO_ADMIN = "Controller?command=gotomainadminpage";
    private static final String GO_TO_REGISTRATION_PAGE = "Controller?command=registration";

    private static final String REGISTRATION_OK = "local.message.registration_ok";
    private static final String ERROR_BUSY = "local.error.login_is_busy";
    private static final String ERROR_DATA = "local.error.data_format";

    private static final String STAFF_TYPE = "staff_type";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long staffType = 0;
        String returnPage;

        HttpSession session = request.getSession(true);
        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);

        UserInfoBuilder userInfoBuilder = new UserInfoBuilder();
        userInfoBuilder.buildUserInfo(request);

        if(isAuth != null )
        {
            returnPage = GO_TO_ADMIN;
            staffType = Long.parseLong(request.getParameter(STAFF_TYPE));
            userInfoBuilder.setRoleId(2);
        } else {
            returnPage = GO_TO_INDEX_PAGE;
            userInfoBuilder.setRoleId(3);
        }

        UserInfo userInfo = userInfoBuilder.getUserInfo();
        ServiceProvider provider = ServiceProvider.getInstance();
        AccountService accountService = provider.getAccountService();
        MedicalHistoryService medicalHistoryService = provider.getMedicalHistoryService();
        StaffService staffService = provider.getStaffService();
        PatientService patientService = provider.getPatientService();
        try {
            accountService.registration(userInfo);
            Visitor visitor = accountService.authorization(userInfo.getLogin(),userInfo.getPassword());
            if(visitor instanceof Patient)
            {
                Patient patient = (Patient) visitor;
                patientService.savePictureToPatient(patient,null);
                MedicalHistory medicalHistory = new MedicalHistory();
                medicalHistory.setPatientId(patient.getId());
                medicalHistoryService.add(medicalHistory);
            }else if(visitor instanceof Staff){
                Staff staff = (Staff) visitor;
                staff.setStaffTypeID(staffType);
                staff.setFirstname(userInfo.getFirstname());
                staff.setLastname(userInfo.getLastname());
                staffService.update(staff);
                staffService.savePictureToStaff(staff,null);
            }
            session.setAttribute(ATTRIBUTE_INFO_MESSAGE,Arrays.asList(REGISTRATION_OK));
            response.sendRedirect(returnPage);
        }catch (LoginIsBusyServiceException e) {
            session.setAttribute(ATTRIBUTE_ERROR_MESSAGE, Arrays.asList(ERROR_BUSY));
            response.sendRedirect(GO_TO_REGISTRATION_PAGE);
        }catch (DataFormatServiceException e) {
            session.setAttribute(ATTRIBUTE_ERROR_MESSAGE,Arrays.asList(ERROR_DATA));
            response.sendRedirect(GO_TO_REGISTRATION_PAGE);
        }catch (ServiceException e) {
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }
    }
}
