package com.ericsson.trainings.gof.patterns.observer.subject;

import java.util.Collection;
import java.util.HashSet;

import com.ericsson.trainings.gof.patterns.observer.event.Event;
import com.ericsson.trainings.gof.patterns.observer.observers.IView;

public class ViewManagerImpl implements ViewManager {
	private Event event;
	private Collection<IView> views;

	public ViewManagerImpl() {
		views = new HashSet<>();
	}

	@Override
	public void addView(final IView iView) {
		if (iView == null) {
			throw new IllegalArgumentException("View must not be null...");
		}
		this.views.add(iView);
	}

	@Override
	public void removeView(final IView iView) {
		if (iView == null) {
			throw new IllegalArgumentException("View must not be null...");
		}
		this.views.remove(iView);
	}

	@Override
	public void setEvent(final Event event) {
		if (event == null) {
			throw new IllegalArgumentException("Event must not be null...");
		}

		this.event = event;
		notifyViews();
	}

	private void notifyViews() {
		for (IView iView : this.views) {
			iView.refresh(this.event);
		}
	}
}
