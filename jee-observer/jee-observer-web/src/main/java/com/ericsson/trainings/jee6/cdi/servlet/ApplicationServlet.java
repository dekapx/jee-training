package com.ericsson.trainings.jee6.cdi.servlet;

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

import com.ericsson.trainings.jee6.cdi.event.CdiEvent;
import com.ericsson.trainings.jee6.cdi.sender.EventSender;

@SuppressWarnings("serial")
@WebServlet(name = "ApplicationServlet", urlPatterns = { "/ApplicationServlet" })
public class ApplicationServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationServlet.class);

	@EJB
	private EventSender eventSender;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("ApplicationServlet invoked...");

		final PrintWriter out = response.getWriter();
		out.println("ApplicationServlet Servlet invoked...");

		for (int i = 0; i < 5; i++) {
			CdiEvent event = new CdiEvent("Test Event " + i + 1);
			eventSender.sendEvent(event);
		}

		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
