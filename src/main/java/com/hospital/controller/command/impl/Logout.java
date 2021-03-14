package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout implements Command {

 

    private static final String GO_TO_INDEX_PAGE ="Controller?command=gotoindexpage";
    private static final String ATTRIBUTE_INFO_MESSAGE = "informationMessage";
    private static final String LOGOUT_OK = "logout successful";
    private static final String ATTRIBUTE_AUTH = "auth";
    private static final String ATTRIBUTE_URL = "url";

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
