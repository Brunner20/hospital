package com.hospital.controller;

import com.hospital.controller.command.Command;
import com.hospital.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hospital.controller.command.CommandParameter.GO_TO_ERROR_PAGE;

public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1788742651L;

    private final CommandProvider provider = new CommandProvider();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String name;
        Command command;
    try {
        name = request.getParameter("command");
        command = provider.takeCommand(name);
        command.execute(request, response);
    }catch (NullPointerException e){
        request.getRequestDispatcher(GO_TO_ERROR_PAGE).forward(request,response); ;
    }

    }

}
