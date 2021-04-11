package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.GO_TO_INDEX_PAGE;

/**
 * Command to go to index page
 */
public class GoToIndexPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(GO_TO_INDEX_PAGE).forward(request, response);
    }
}
