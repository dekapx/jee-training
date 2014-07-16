package com.ericsson.trainings.gof.patterns.singleton.early;

public class MySingleton {
	private static final MySingleton INSTANCE = new MySingleton();

	private MySingleton() {
	}

	public static MySingleton getInstance() {
		return INSTANCE;
	}

	public String sayHello(final String arg) {
		return "Hello !!! " + arg;
	}
}
