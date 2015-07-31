package com.ericsson.trainings.jee.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "JmsQueueLookupServlet", urlPatterns = { "/JmsQueueLookupServlet" })
public class JmsQueueLookupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		final PrintWriter writer = response.getWriter();
		writer.write("<B>JmsQueueLookupServlet</B><BR>");

		final String queueJndiName = "java:/queue/request";
		final Destination queue = findQueueByJndi(queueJndiName);
		writer.write("<BR>Queue exists validJndiName: " + queue);
		writer.close();
	}

	private Destination findQueueByJndi(final String jndi) {
		Destination destination = null;
		try {
			Context jndiContext = new InitialContext();
			destination = (Destination) jndiContext.lookup(jndi);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return destination;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
