package com.ericsson.trainings.gof.patterns.singleton.lazy;

public final class MySingletonDoubleCheck {
	private static MySingletonDoubleCheck instance = null;

	private MySingletonDoubleCheck() {
		System.out.println("MySingletonSynchronized initialized...");
	}

	public static MySingletonDoubleCheck getInstance() {
		if (instance == null) {
			synchronized (MySingletonDoubleCheck.class) {
				if (instance == null) {
					instance = new MySingletonDoubleCheck();
				}
			}
		}

		return instance;
	}

	public String sayHello(final String arg) {
		return "Hello !!! " + arg;
	}

}
