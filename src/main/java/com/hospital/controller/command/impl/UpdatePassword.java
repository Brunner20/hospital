package com.hospital.controller.command.impl;

import com.hospital.bean.Patient;
import com.hospital.bean.Staff;
import com.hospital.controller.command.Command;
import com.hospital.service.AccountService;
import com.hospital.service.ServiceProvider;
import com.hospital.service.exception.DataFormatServiceException;
import com.hospital.service.exception.DataNotFoundServiceException;
import com.hospital.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

import static com.hospital.controller.command.CommandParameter.*;

/**
 * Command when the password is updated
 */
public class UpdatePassword  implements Command{

    private static final String GO_TO_UPDATE_PASSWORD_PAGE = "Controller?command=gotopasswordupdatepage";

    private static final String OLD_PASSWORD = "old_password";
    private static final String NEW_PASSWORD = "new_password";

    private static final String ERROR_OLD_PASS = "local.error.invalid_old_password";
    private static final String ERROR_NEW_PASS = "local.error.invalid_new_password";

    private static final String UPDATE_PASSWORD_OK = "local.info.password_updated";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if(session == null) {
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }
        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        if (isAuth == null || !isAuth ) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        String oldPass = request.getParameter(OLD_PASSWORD);
        String newPass = request.getParameter(NEW_PASSWORD);
        ServiceProvider provider = ServiceProvider.getInstance();
        long accountId = 0;
        try {
            if (session.getAttribute(ATTRIBUTE_ROLE).equals(ROLE_PATIENT)) {
                Patient patient = provider.getPatientService().getPatientById((Long) session.getAttribute(ATTRIBUTE_VISITOR_ID));
                accountId = patient.getAccountID();
            } else if (session.getAttribute(ATTRIBUTE_ROLE).equals(ROLE_DOCTOR)||session.getAttribute(ATTRIBUTE_ROLE).equals(ROLE_NURSE)) {
                Staff staff = provider.getStaffService().getStaffById((Long) session.getAttribute(ATTRIBUTE_VISITOR_ID));
                accountId = staff.getAccountID();
            }

            AccountService accountService = provider.getAccountService();
            accountService.updatePassword(accountId,oldPass,newPass);
            session.setAttribute(ATTRIBUTE_INFO_MESSAGE,Arrays.asList(UPDATE_PASSWORD_OK));
            session.setAttribute(ATTRIBUTE_URL,GO_TO_MAIN_PAGE);
            response.sendRedirect(GO_TO_MAIN_PAGE);
        } catch (DataFormatServiceException e){
            session.setAttribute(ATTRIBUTE_ERROR_MESSAGE, Arrays.asList(ERROR_NEW_PASS));
            session.setAttribute(ATTRIBUTE_URL,GO_TO_UPDATE_PASSWORD_PAGE);
            response.sendRedirect(GO_TO_UPDATE_PASSWORD_PAGE);
        }catch (DataNotFoundServiceException e){
            session.setAttribute(ATTRIBUTE_ERROR_MESSAGE, Arrays.asList(ERROR_OLD_PASS));
            session.setAttribute(ATTRIBUTE_URL,GO_TO_UPDATE_PASSWORD_PAGE);
            response.sendRedirect(GO_TO_UPDATE_PASSWORD_PAGE);
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_UPDATE_PASSWORD_PAGE);
            response.sendRedirect(GO_TO_UPDATE_PASSWORD_PAGE);
        }

    }
}
