package com.ericsson.trainings.gof.patterns.singleton.lazy;

public final class MySingletonSingleCheck {
	private static volatile MySingletonSingleCheck instance = null;

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

	public static void main(String[] args) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				MySingletonSingleCheck single = MySingletonSingleCheck.getInstance();
				single.sayHello("test");
			}
		};
		for (int i = 0; i < 500; i++) {
			new Thread(task).start();
		}
	}
}
