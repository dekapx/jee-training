package com.ericsson.trainings.gof.patterns.observer;

public interface ViewManager {
	void addView(IView iView);

	void removeView(IView iView);

	void setEvent(Event event);
}
