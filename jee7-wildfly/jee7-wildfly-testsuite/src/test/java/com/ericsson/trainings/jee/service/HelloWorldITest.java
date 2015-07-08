package com.ericsson.trainings.jee.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Future;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee.service.api.HelloWorldLocal;
import com.ericsson.trainings.jee.service.impl.HelloWorldBean;
import com.ericsson.trainings.jee.service.util.TextMessageFormatter;

@RunWith(Arquillian.class)
public class HelloWorldITest {
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldITest.class);

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addClasses(TextMessageFormatter.class, HelloWorldLocal.class, HelloWorldBean.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private HelloWorldLocal helloWorldEJB;

	@Test
	@InSequence(1)
	public void testSayHello() throws Exception {
		logger.info("Calling Sync HelloWorld EJB from Arquillian...");

		final String expected = "Hello !!! Test";
		final String output = helloWorldEJB.sayHello("Test");

		assertNotNull(output);
		assertEquals(expected, output);
	}

	@Test
	@InSequence(2)
	public void testSayHelloAsync() throws Exception {
		logger.info("Calling Async HelloWorld EJB from Arquillian...");

		final String expected = "Hello !!! Test";
		final Future<String> future = helloWorldEJB.sayHelloAsync("Test");

		assertNotNull(future);
		assertEquals(expected, future.get());
		assertTrue(future.isDone());
	}
}
