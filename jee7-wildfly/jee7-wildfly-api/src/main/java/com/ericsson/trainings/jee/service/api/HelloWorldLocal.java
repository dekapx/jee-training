package com.ericsson.trainings.jee.service.api;

import java.util.concurrent.Future;

public interface HelloWorldLocal {
	
	String sayHello(String arg);

	Future<String> sayHelloAsync(String arg);
}
