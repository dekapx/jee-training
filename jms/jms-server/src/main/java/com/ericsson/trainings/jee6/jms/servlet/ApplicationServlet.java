package com.ericsson.trainings.jee6.jms.servlet;

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

import com.ericsson.trainings.jee6.jms.p2p.QueueSenderBean;

/**
 * ApplicationServlet sends text message to <code>QueueSenderBean</code>.
 * 
 * @author KAPIL KUMAR
 * @version 1.0
 */
@WebServlet(name = "ApplicationServlet", urlPatterns = { "/ApplicationServlet" })
public class ApplicationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationServlet.class);

	@EJB
	private QueueSenderBean queueSenderBean;

	@Override
	public void init() throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("ApplicationServlet invoked...");

		final PrintWriter out = response.getWriter();
		out.println("Application Servlet invoked...");

		for (int i = 0; i < 5; i++) {
			final String textMessage = "Text Message-" + i;
			queueSenderBean.sendMessage(textMessage);
		}

		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
