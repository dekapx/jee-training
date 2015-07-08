package com.ericsson.trainings.jee.service.api;

import javax.ejb.Local;

@Local
public interface HelloWorldLocal {
	String sayHello(String arg);
}
