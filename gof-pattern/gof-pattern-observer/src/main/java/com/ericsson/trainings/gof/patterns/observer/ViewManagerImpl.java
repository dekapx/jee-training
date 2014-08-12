package com.ericsson.trainings.gof.patterns.observer;

import java.util.Collection;
import java.util.HashSet;

public class ViewManagerImpl implements ViewManager {
	private Event event;
	private Collection<IView> views;

	public ViewManagerImpl() {
		views = new HashSet<>();
	}

	@Override
	public void addView(final IView iView) {
		this.views.add(iView);
	}

	@Override
	public void removeView(final IView iView) {
		this.views.remove(iView);
	}

	@Override
	public void setEvent(final Event event) {
		this.event = event;
		notifyViews();
	}

	private void notifyViews() {
		for (IView iView : this.views) {
			iView.refresh(this.event);
		}
	}
}
