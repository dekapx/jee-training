package com.ericsson.trainings.gof.patterns.singleton.enumtype;

public enum MySingleton {
	INSTANCE;

	public String sayHello(final String arg) {
		return "Hello !!! " + arg;
	}

}
