package com.ericsson.trainings.jse.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ericsson.trainings.jse.service.FileWriter;
import com.ericsson.trainings.jse.service.impl.CsvFileWriter;
import com.ericsson.trainings.jse.service.impl.DummyFileWriter;

public class ServiceManagerTest {
	private ServiceManager serviceManager;

	@Before
	public void setUp() throws Exception {
		serviceManager = ServiceManager.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void getService_withNullArgument_throwsIllegalArgumentException() {
		serviceManager.getService(null);
	}

	@Test
	public void getService_withValidArgument_returnValidImplementation() {
		final FileWriter fileWriter = serviceManager.getService(CsvFileWriter.class);
		assertNotNull(fileWriter);
		assertTrue(fileWriter instanceof CsvFileWriter);

		fileWriter.write("sample contents...");
	}

	@Test
	public void getService_withDummyArgument_returnValidDummyImplementation() {
		final FileWriter fileWriter = serviceManager.getService(DummyFileWriter.class);
		assertNotNull(fileWriter);
		assertTrue(fileWriter instanceof DummyFileWriter);

		fileWriter.write("sample contents...");
	}
}
