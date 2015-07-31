package com.ericsson.trainings.gof.patterns.singleton.serialize;

import java.io.ObjectStreamException;
import java.io.Serializable;

@SuppressWarnings("serial")
public class MySingleton implements Serializable {
	private static MySingleton instance;

	private String state;

	private MySingleton() {
	}

	public static MySingleton getInstance() {
		if (instance == null) {
			synchronized (MySingleton.class) {
				if (instance == null) {
					instance = new MySingleton();
				}
			}
		}

		return instance;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	private Object readResolve() throws ObjectStreamException {
		return instance;
	}
}
