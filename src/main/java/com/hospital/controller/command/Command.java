package com.hospital.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * The interface Command.
 */
public interface Command {

    /**
     * Execute request.
     * @param request the request
     * @throws ServletException the command exception
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
