package com.ericsson.trainings.jee6.timer.servlet;

import java.io.IOException;
import java.util.Date;

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
		// runs every hour
		expression.hour("*");

		// runs every minute
		expression.minute("*");

		// runs every 5 second
		expression.second("*/5");

		return expression;
	}

	private ScheduleExpression create7SecScheduleExpression() {
		final ScheduleExpression expression = new ScheduleExpression();

		// runs every hour
		expression.hour("*");

		// runs every minute
		expression.minute("*");

		// runs every 7 second
		expression.second("*/7");

		return expression;
	}

	@SuppressWarnings("unused")
	private ScheduleExpression createExpressionForSpecificTime() {
		// one minute delay to start the scheduling
		final long timeInterval = 60000;

		// start time with one minute delay
		final long timeMillis = System.currentTimeMillis() + timeInterval;
		final ScheduleExpression expression = new ScheduleExpression();

		// the start time for scheduler to trigger..
		expression.start(new Date(timeMillis));

		// runs every hour
		expression.hour("*");

		// runs every 2 minutes interval
		expression.minute("*/3");

		// marked zero means seconds are not considered
		expression.second(0);

		return expression;
	}

	@SuppressWarnings("unused")
	private ScheduleExpression createExpressionDateRange() {
		final ScheduleExpression expression = new ScheduleExpression();

		// trigger between 20th and 27th of every month
		expression.dayOfMonth("20-27");

		// trigger at 16th hour of the date i.e. 16:00 PM
		expression.hour("16");

		// runs every minute
		expression.minute("*");

		// runs every 15 seconds
		expression.second("*/15");

		return expression;
	}
}
