package com.ericsson.trainings.jee6.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);

	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		LOGGER.info("--------------------- HelloWorldBean call intetrcepted before invocarion ---------------------");

		LOGGER.info("--------------------- Method Name : {} ---------------------", context.getMethod().getName());
		LOGGER.info("--------------------- Method Parameters : {} ---------------------", context.getParameters());

		final Class<?>[] clazzTypes = context.getMethod().getParameterTypes();
		for (Class<?> clazz : clazzTypes) {
			LOGGER.info("--------------------- Method Parameter Type : {} ---------------------", clazz.getSimpleName());
		}

		final Object result = context.proceed();

		LOGGER.info("--------------------- HelloWorldBean call intetrcepted after invocarion ---------------------");
		LOGGER.info("--------------------- Method output is [{}] ---------------------", result);

		return result;
	}
}
