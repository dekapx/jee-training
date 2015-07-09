package com.ericsson.trainings.jee.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.ericsson.trainings.jee.service.util.TextMessageFormatter;

@RunWith(MockitoJUnitRunner.class)
public class HelloWorldBeanTest {
	private HelloWorldBean objUnderTest;

	@Mock
	private TextMessageFormatter mockedTextMessageFormatter;

	@Before
	public void setUp() throws Exception {
		when(mockedTextMessageFormatter.format(any(String.class))).thenAnswer(new Answer<String>() {
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				return "Hello !!! JEE-7";
			}
		});
		// initialize the object to be tested...
		objUnderTest = new HelloWorldBean();

		// setting the mocked dependency
		Whitebox.setInternalState(objUnderTest, "formatter", mockedTextMessageFormatter);

	}

	@After
	public void tearDown() throws Exception {
		objUnderTest = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSayHello_withNullArgument_throwsIllegalArgumentException() {
		objUnderTest.sayHello(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSayHello_withEmptyArgument_throwsIllegalArgumentException() {
		objUnderTest.sayHello("");
	}

	@Test
	public void testSayHello_withValidArgument_returnFormattedOutput() {
		final String expectedOutput = "Hello !!! JEE-7";
		final String actualOutput = objUnderTest.sayHello("JEE-7");
		assertEquals(expectedOutput, actualOutput);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSayHelloAsync_withNullArgument_throwsIllegalArgumentException() {
		objUnderTest.sayHelloAsync(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSayHelloAsync_withEmptyArgument_throwsIllegalArgumentException() {
		objUnderTest.sayHelloAsync("");
	}

	@Test
	public void testSayHelloAsync_withValidArgument_returnFormattedOutput() throws Exception {
		final String expectedOutput = "Hello !!! JEE-7";
		final Future<String> actualOutput = objUnderTest.sayHelloAsync("JEE-7");
		assertNotNull(actualOutput);
		assertEquals(expectedOutput, actualOutput.get());
		assertTrue(actualOutput.isDone());
	}

}
