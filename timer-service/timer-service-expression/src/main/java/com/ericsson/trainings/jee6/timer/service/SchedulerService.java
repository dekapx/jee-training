package com.ericsson.trainings.jee6.timer.service;

import javax.ejb.ScheduleExpression;

import com.ericsson.trainings.jee6.timer.task.TimerTask;

public interface SchedulerService {
	void createTimer(TimerTask timerTask, ScheduleExpression expression, boolean persistant);

	void cancelTimer(final String timerId);
}
