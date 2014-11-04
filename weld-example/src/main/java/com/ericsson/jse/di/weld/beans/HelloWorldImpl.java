package com.ericsson.jse.di.weld.beans;

import javax.inject.Inject;

import com.ericsson.jse.di.weld.util.MessageFormatter;

public class HelloWorldImpl implements HelloWorld {
	@Inject
	private MessageFormatter formatter;

	@Override
	public String sayHello(String arg) {
		return formatter.format(arg);
	}

}
