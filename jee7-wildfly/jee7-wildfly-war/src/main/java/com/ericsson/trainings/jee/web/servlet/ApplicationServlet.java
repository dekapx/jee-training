package com.ericsson.trainings.jee.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ericsson.trainings.jee.jms.p2p.QueueSenderBean;

@WebServlet(name = "ApplicationServlet", urlPatterns = { "/ApplicationServlet" })
public class ApplicationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private QueueSenderBean queueSenderBean;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final PrintWriter writer = response.getWriter();
		writer.write("Welcome to Wildfly JEE-7");

		for (int i = 0; i < 5; i++) {
			queueSenderBean.sendMessage("text message - " + i + 1);
		}

		writer.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
