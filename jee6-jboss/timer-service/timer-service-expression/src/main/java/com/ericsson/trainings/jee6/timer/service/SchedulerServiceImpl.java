package com.ericsson.trainings.jee6.timer.service;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.timer.task.TimerTask;

@Stateless
@Local(SchedulerService.class)
public class SchedulerServiceImpl implements SchedulerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	@Resource
	private TimerService timerService;

	@Timeout
	public void timeout(final Timer timer) {
		final TimerTask timerTask = (TimerTask) timer.getInfo();
		LOGGER.info("Executing timer job for timer task [{}]", timerTask.getTimerJob());
	}

	@Override
	public void createTimer(TimerTask timerTask, ScheduleExpression expression, boolean persistant) {
		if (timerTask == null) {
			throw new IllegalArgumentException("Timer job must not be null.");
		}

		if (expression == null) {
			throw new IllegalArgumentException("Schedule Expression must not be null.");
		}

		final TimerConfig timerConfig = new TimerConfig(timerTask, persistant);
		LOGGER.info("new job is schedules for timer task {} ", timerTask.getTimerJob());

		timerService.createCalendarTimer(expression, timerConfig);
	}

	@Override
	public void cancelTimer(final String timerId) {
		final Collection<Timer> timers = timerService.getTimers();
		for (Timer timer : timers) {
			final TimerTask timerTask = (TimerTask) timer.getInfo();
			if (timerTask.getTimerId().equals(timerId)) {
				timer.cancel();
				break;
			}
		}
	}

}
