package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.*;

public class Logout implements Command {

 


    private static final String ATTRIBUTE_INFO_MESSAGE = "informationMessage";
    private static final String LOGOUT_OK = "logout successful";



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(true);

        if(session != null) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            session.removeAttribute(ATTRIBUTE_AUTH);
        }
        request.setAttribute(ATTRIBUTE_INFO_MESSAGE,LOGOUT_OK);
        response.sendRedirect(GO_TO_INDEX_PAGE);
    }
}
