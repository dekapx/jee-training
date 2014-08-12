package com.ericsson.trainings.gof.patterns.observer;

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

		// unsubscribe property view from events
		viewManager.removeView(propertyView);

		Event xmlFileEvent = new Event("XML");
		viewManager.setEvent(xmlFileEvent);
	}
}
