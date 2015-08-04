package com.ericsson.trainings.jse.weld.cdi.util;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileWriterUtilTest {
	private FileWriterUtil fileWriterUtil;

	@Before
	public void setUp() throws Exception {
		try {
			final WeldContainer weld = new Weld().initialize();
			fileWriterUtil = weld.instance().select(FileWriterUtil.class).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		fileWriterUtil = null;
	}

	@Test
	public void writeCsvFileContents() {
		fileWriterUtil.writeCsvFile("sample text");
	}

}
