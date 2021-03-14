package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocale implements Command {

    private static final String ATTRIBUTE_URL = "url";
    private static final String ATTRIBUTE_LOCAL = "locale";
    private static final String PARAMETER_LOC = "loc";

    private static final String GO_TO_INDEX_PAGE = "Controller?command=gotoindexpage";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        session.setAttribute(ATTRIBUTE_LOCAL, request.getParameter(PARAMETER_LOC));

        String url = (String) session.getAttribute(ATTRIBUTE_URL);
        if(url == null|| url.isEmpty()){
            url = GO_TO_INDEX_PAGE;
        }
        response.sendRedirect(url);

    }
}
