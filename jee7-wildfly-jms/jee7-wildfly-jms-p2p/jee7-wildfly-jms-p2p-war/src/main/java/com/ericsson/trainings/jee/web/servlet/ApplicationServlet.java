package com.ericsson.trainings.jee.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ericsson.trainings.jee.jms.MessageSenderLocal;

@WebServlet(name = "ApplicationServlet", urlPatterns = { "/ApplicationServlet" })
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int MESSAGE_COUNT = 5;

	@Inject
	private MessageSenderLocal messageSender;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		final PrintWriter writer = response.getWriter();
		writer.write("<B>Welcome to Wildfly JEE-7</B><BR><BR>");

		for (int i = 0; i < MESSAGE_COUNT; i++) {
			final String message = "sample text message-" + (i + 1);
			messageSender.sendMessage(message);
			writer.write("Sending [ " + message + " ] to the queue...<BR>");
		}

		writer.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
