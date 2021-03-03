package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout implements Command {

  //  public static final Logger logger = LogManager.getLogger(Logout.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        if(session != null) {
            session.removeAttribute("auth");
        }
       // logger.log(Level.INFO,"logout ok");
        response.sendRedirect("Controller?command=gotoindexpage&message=logout ok");
    }
}
