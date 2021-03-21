package com.hospital.controller.command.impl;

import com.hospital.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToIndexPage implements Command {

	private static final String PATH_TO_INDEX = "/WEB-INF/jsp/login.jsp";
	private static final String GO_TO_INDEX_PAGE = "Controller?command=gotoindexpage";
	private static final String ATTRIBUTE_URL = "url";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_INDEX);
		requestDispatcher.forward(request, response);

	}

}
