package com.ericsson.trainings.gof.patterns.observer.jseclient;

import com.ericsson.trainings.gof.patterns.observer.event.Event;
import com.ericsson.trainings.gof.patterns.observer.observers.IView;
import com.ericsson.trainings.gof.patterns.observer.observers.OutlineView;
import com.ericsson.trainings.gof.patterns.observer.observers.PropertyView;
import com.ericsson.trainings.gof.patterns.observer.subject.ViewManager;
import com.ericsson.trainings.gof.patterns.observer.subject.ViewManagerImpl;

public class JseTest {
	public static void main(String[] args) {
		// create view manager
		ViewManager viewManager = new ViewManagerImpl();

		// create outline view
		IView outlineView = new OutlineView();
		// create property view
		IView propertyView = new PropertyView();

		// subscribe for view manager events
		viewManager.addView(outlineView);
		viewManager.addView(propertyView);

		// create a java file opening event
		Event javaFileEvent = new Event("Java");
		viewManager.setEvent(javaFileEvent);

		System.out.println("-------------------------------");

		// remove property view from events
		viewManager.removeView(propertyView);

		Event xmlFileEvent = new Event("XML");
		viewManager.setEvent(xmlFileEvent);
	}
}
