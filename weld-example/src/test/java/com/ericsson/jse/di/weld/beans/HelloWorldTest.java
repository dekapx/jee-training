package com.ericsson.jse.di.weld.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelloWorldTest {
	private HelloWorld helloWorld;

	@Before
	public void setUp() throws Exception {
		final WeldContainer weld = new Weld().initialize();
		helloWorld = weld.instance().select(HelloWorldImpl.class).get();
	}

	@After
	public void tearDown() throws Exception {
		helloWorld = null;
	}

	@Test
	public void testSayHello() {
		final String actual = helloWorld.sayHello("Kapx");
		assertNotNull(actual);
		assertEquals("Hello !!! Kapx", actual);
	}

}
