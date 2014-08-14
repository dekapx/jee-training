package com.ericsson.trainings.jee6.timer.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.timer.service.SchedulerService;
import com.ericsson.trainings.jee6.timer.task.TimerTask;

@SuppressWarnings("serial")
@WebServlet(name = "ApplicationServlet", urlPatterns = { "/ApplicationServlet" })
public class ApplicationServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationServlet.class);

	@EJB
	private SchedulerService schedulerService;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("ApplicationServlet invoked...");

		TimerTask timerTask5Sec = new TimerTask("5SecROP", "Timer Task for 5 Seconds");
		LOGGER.info("Register timer task for 5 Seconds ROP");
		schedulerService.createTimer(timerTask5Sec, create5SecScheduleExpression(), false);

		TimerTask timerTask7Sec = new TimerTask("5SecROP", "Timer Task for 7 Seconds");
		LOGGER.info("Register timer task for 7 Seconds ROP");
		schedulerService.createTimer(timerTask7Sec, create7SecScheduleExpression(), false);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private ScheduleExpression create5SecScheduleExpression() {
		final ScheduleExpression expression = new ScheduleExpression();

		expression.hour("*");
		expression.minute("*");
		expression.second("*/5");

		return expression;
	}

	private ScheduleExpression create7SecScheduleExpression() {
		final ScheduleExpression expression = new ScheduleExpression();

		expression.hour("*");
		expression.minute("*");
		expression.second("*/7");

		return expression;
	}
}
