package com.ericsson.trainings.jse.weld.cdi.beans;

import javax.inject.Inject;

import com.ericsson.trainings.jse.weld.cdi.util.MessageFormatter;

public class HelloWorldImpl implements HelloWorld {
	@Inject
	private MessageFormatter formatter;

	@Override
	public String sayHello(String arg) {
		return formatter.format(arg);
	}

}
