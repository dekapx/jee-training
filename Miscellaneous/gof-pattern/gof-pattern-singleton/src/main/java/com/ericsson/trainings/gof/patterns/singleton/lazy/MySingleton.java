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

	public static void main(String[] args) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				MySingleton single = MySingleton.getInstance();
				single.sayHello("test");
			}
		};
		for (int i = 0; i < 500; i++) {
			new Thread(task).start();
		}
	}
}
