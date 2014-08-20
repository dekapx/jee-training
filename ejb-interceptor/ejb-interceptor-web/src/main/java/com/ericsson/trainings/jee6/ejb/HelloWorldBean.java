package com.ericsson.trainings.jee6.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.interceptor.LoggingInterceptor;

@Stateless
@Local(HelloWorldLocal.class)
@Interceptors(LoggingInterceptor.class)
public class HelloWorldBean implements HelloWorldLocal {
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldBean.class);

	@Override
	public String sayHello(String arg) {
		LOGGER.info("sayHello invoked with parameter [{}]", arg);
		return "Hello !!! " + arg;
	}

}
