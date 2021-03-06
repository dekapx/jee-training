package com.ericsson.trainings.jse.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicProxy implements InvocationHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(DynamicProxy.class);

	private Object object;

	public static Object newInstance(final Object object) {
		return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new DynamicProxy(object));
	}

	private DynamicProxy(final Object object) {
		this.object = object;
	}

	@Override
	public Object invoke(final Object proxy, final Method method, final Object[] args) throws Exception {
		Object result = null;

		LOGGER.info("Before invocation - method name - [{}]", method.getName());
		LOGGER.info("Before invocation - method arguments - [{}]", args);
		try {
			result = method.invoke(object, args);
			LOGGER.info("After invocation - method output - [{}]", result);
		} catch (InvocationTargetException e) {
			LOGGER.error("Exception while invoking proxy call...", e);
			throw new Exception(e);
		} catch (Exception e) {
			LOGGER.error("Exception while invoking proxy call...", e);
			throw new Exception(e);
		}

		return result;
	}
}