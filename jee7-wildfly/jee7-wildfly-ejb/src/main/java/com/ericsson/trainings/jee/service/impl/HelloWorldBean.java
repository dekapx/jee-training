package com.ericsson.trainings.jee.service.impl;

import javax.ejb.Stateless;

import com.ericsson.trainings.jee.service.api.HelloWorldLocal;

@Stateless
public class HelloWorldBean implements HelloWorldLocal {

	@Override
	public String sayHello(String arg) {
		return "Hello !!! " + arg;
	}

}
