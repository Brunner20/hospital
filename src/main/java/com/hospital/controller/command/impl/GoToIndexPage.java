package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToIndexPage implements Command {

	private static final String PATH_TO_INDEX = "/WEB-INF/jsp/main_index.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_INDEX);
			requestDispatcher.forward(request, response);

	}

}
