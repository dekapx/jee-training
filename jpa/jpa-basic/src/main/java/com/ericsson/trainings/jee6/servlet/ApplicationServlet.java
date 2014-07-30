package com.ericsson.trainings.jee6.servlet;

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

import com.ericsson.trainings.jee6.entity.Contact;
import com.ericsson.trainings.jee6.service.ContactService;

@WebServlet(name = "ApplicationServlet", urlPatterns = { "/ApplicationServlet" })
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationServlet.class);

	@EJB
	private ContactService contactService;

	@Override
	public void init() throws ServletException {
		LOGGER.info("ApplicationServlet initialized...");
	}

	@Override
	public void destroy() {
		LOGGER.info("ApplicationServlet destroyed...");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		final PrintWriter out = response.getWriter();
		out.println("Adding new contact to database...");

		final String firstName = request.getParameter("fname");
		final String lastName = request.getParameter("lname");
		final String email = request.getParameter("email");

		LOGGER.info("invoking ContactService to persist contact entity...");
		Contact contact = new Contact();
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setEmail(email);

		contact = contactService.save(contact);
		out.println(contact);
		out.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
