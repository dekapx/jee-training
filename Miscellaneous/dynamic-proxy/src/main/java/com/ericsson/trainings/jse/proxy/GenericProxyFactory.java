package com.ericsson.trainings.jse.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.ericsson.trainings.jse.subject.HelloWorld;
import com.ericsson.trainings.jse.subject.HelloWorldImpl;

public enum GenericProxyFactory {
	INSTANCE;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T getObjectByClassType(final Class<T> clazz) {
		final Constructor constructor = clazz.getConstructors()[0];
		try {
			return (T) DynamicProxy.newInstance(constructor.newInstance());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		final GenericProxyFactory factory = GenericProxyFactory.INSTANCE;
		HelloWorld helloWorld = factory.getObjectByClassType(HelloWorldImpl.class);
		helloWorld.sayHello("test");
	}
}
