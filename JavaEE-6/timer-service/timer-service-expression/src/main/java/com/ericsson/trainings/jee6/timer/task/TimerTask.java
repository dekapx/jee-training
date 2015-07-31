package com.ericsson.trainings.jee6.timer.task;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TimerTask implements Serializable {
	private String timerId;
	private String timerJob;

	public TimerTask(final String timerId, final String timerJob) {
		this.timerId = timerId;
		this.timerJob = timerJob;
	}

	public String getTimerId() {
		return timerId;
	}

	public String getTimerJob() {
		return timerJob;
	}

}
