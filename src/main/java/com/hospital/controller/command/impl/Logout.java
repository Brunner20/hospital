package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.*;

/**
 * Command where user can log out from their profile
 */
public class Logout implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(true);

        if(session != null) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            session.removeAttribute(ATTRIBUTE_AUTH);
            session.removeAttribute(ATTRIBUTE_ROLE);
        }
        response.sendRedirect(GO_TO_INDEX_PAGE);
    }
}
