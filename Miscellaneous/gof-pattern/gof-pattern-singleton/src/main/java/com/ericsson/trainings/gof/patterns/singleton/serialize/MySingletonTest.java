package com.ericsson.trainings.gof.patterns.singleton.serialize;

import com.ericsson.trainings.gof.patterns.singleton.utils.SerializationUtil;

public class MySingletonTest {
	public static void main(String[] args) throws Exception {
		final MySingleton singleton = MySingleton.getInstance();
		singleton.setState("State-X");

		SerializationUtil<MySingleton> serializationUtil = new SerializationUtil<MySingleton>();
		serializationUtil.serialize(singleton);

		final MySingleton singletonCopy = serializationUtil.deserialize(MySingleton.class);
		System.out.println("------------------ Before Modification ------------------");
		System.out.println("Orignal : " + singleton.getState());
		System.out.println("Copy : " + singletonCopy.getState());

		singletonCopy.setState("StateY");

		System.out.println("------------------ After Modification ------------------");
		System.out.println("Orignal : " + singleton.getState());
		System.out.println("Copy : " + singletonCopy.getState());
	}

}
