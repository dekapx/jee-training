package com.ericsson.trainings.gof.patterns.factory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ericsson.trainings.gof.patterns.beans.CsvFileWriter;
import com.ericsson.trainings.gof.patterns.beans.FileWriter;
import com.ericsson.trainings.gof.patterns.beans.TextFileWriter;
import com.ericsson.trainings.gof.patterns.beans.XmlFileWriter;

public class FileWriterFactoryTest {
	private FileWriterFactory fileWriterFactory;

	@Before
	public void setUp() throws Exception {
		fileWriterFactory = FileWriterFactory.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		fileWriterFactory = null;
	}

	@Test
	public void testGetFileWriter() throws Exception {
		final FileWriter xmlFileWriter = fileWriterFactory.getFileWriter(XmlFileWriter.class.getName());
		assertNotNull(xmlFileWriter);
		assertTrue(xmlFileWriter instanceof XmlFileWriter);
		xmlFileWriter.write("sample text");

		final FileWriter textFileWriter = fileWriterFactory.getFileWriter(TextFileWriter.class.getName());
		assertNotNull(textFileWriter);
		assertTrue(textFileWriter instanceof TextFileWriter);
		textFileWriter.write("sample text");

		final FileWriter csvFileWriter = fileWriterFactory.getFileWriter(CsvFileWriter.class.getName());
		assertNotNull(csvFileWriter);
		assertTrue(csvFileWriter instanceof CsvFileWriter);
		csvFileWriter.write("sample text");
	}

}
