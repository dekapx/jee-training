package com.ericsson.trainings.gof.patterns.singleton.lazy;

public final class MySingletonSingleCheck {
	private static MySingletonSingleCheck instance = null;

	private MySingletonSingleCheck() {
		System.out.println("MySingletonSingleCheck initialized...");
	}

	public static MySingletonSingleCheck getInstance() {
		if (instance == null) {
			synchronized (MySingletonSingleCheck.class) {
				instance = new MySingletonSingleCheck();
			}
		}

		return instance;
	}

	public String sayHello(final String arg) {
		return "Hello !!! " + arg;
	}

}
