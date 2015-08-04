package com.ericsson.trainings.jee.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee.cdi.event.CdiEvent;
import com.ericsson.trainings.jee.cdi.sender.EventSender;

@WebServlet(name = "ApplicationServlet", urlPatterns = { "/ApplicationServlet" })
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationServlet.class);

	@EJB
	private EventSender eventSender;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		final PrintWriter writer = response.getWriter();
		writer.write("<B>Welcome to Wildfly JEE-7</B><BR><BR>");

		for (int i = 0; i < 5; i++) {
			final CdiEvent cdiEvent = new CdiEvent("CdiEvent - " + (i + 1));
			eventSender.sendEvent(cdiEvent);
		}

		writer.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
