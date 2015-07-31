package com.ericsson.trainings.gof.patterns.singleton.enumtype;

public enum MySingleton {
	INSTANCE;

	public String sayHello(final String arg) {
		return "Hello !!! " + arg;
	}

	public static void main(String[] args) {
		MySingleton singleton = MySingleton.INSTANCE;
		singleton.sayHello("test");
	}
}
