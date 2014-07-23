package com.ericsson.trainings.jee6.jms.p2p;

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

/**
 * QueueServlet sends text message to <code>QueueSenderBean</code>.
 * 
 * @author KAPIL KUMAR
 * @version 1.0
 */
@WebServlet(name = "QueueServlet", urlPatterns = { "/QueueServlet" })
public class QueueServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(QueueServlet.class);

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
		LOGGER.info("QueueServlet invoked...");

		final PrintWriter out = response.getWriter();
		out.println("QueueServlet Servlet invoked...");

		for (int i = 0; i < 5; i++) {
			final String textMessage = "Text Message-" + (i + 1);
			queueSenderBean.sendMessage(textMessage);
		}

		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
