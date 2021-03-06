package com.ericsson.trainings.jee.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ericsson.trainings.jee.cdi.beans.FileWriter;
import com.ericsson.trainings.jee.cdi.util.BeanFactory;

@WebServlet(name = "ApplicationServlet", urlPatterns = { "/ApplicationServlet" })
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BeanFactory beanFactory;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		final PrintWriter writer = response.getWriter();
		writer.write("<B>Welcome to Wildfly JEE-7</B><BR><BR>");

		final FileWriter textFileWriter = beanFactory.getBeanInstanceByName("textFileWriter");
		textFileWriter.write("sample contents...");

		writer.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
