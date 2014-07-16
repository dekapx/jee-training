package com.ericsson.trainings.gof.patterns.singleton.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil<T> {
	public void serialize(T object) throws Exception {
		final String fileName = object.getClass().getSimpleName() + ".ser";

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
		objectOutputStream.writeObject(object);
		System.out.println("Object of class [" + object.getClass() + "] is serialized...");
		objectOutputStream.close();
	}

	@SuppressWarnings({ "unchecked", "resource" })
	public T deserialize(Class<T> clazz) throws Exception {
		final String fileName = clazz.getSimpleName() + ".ser";
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
		return (T) objectInputStream.readObject();
	}
}
