package com.ericsson.trainings.gof.patterns.singleton.lazy;

public final class MySingleton {
	private static MySingleton instance = null;

	private MySingleton() {
		System.out.println("MySingleton initialized...");
	}

	public static MySingleton getInstance() {
		if (instance == null) {
			instance = new MySingleton();
		}

		return instance;
	}

	public String sayHello(final String arg) {
		return "Hello !!! " + arg;
	}

}
