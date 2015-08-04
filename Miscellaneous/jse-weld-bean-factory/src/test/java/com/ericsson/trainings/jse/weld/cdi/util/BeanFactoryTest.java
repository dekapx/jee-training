package com.ericsson.trainings.jse.weld.cdi.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ericsson.trainings.jse.weld.cdi.beans.CsvFileWriter;
import com.ericsson.trainings.jse.weld.cdi.beans.FileWriter;
import com.ericsson.trainings.jse.weld.cdi.beans.TextFileWriter;
import com.ericsson.trainings.jse.weld.cdi.beans.XmlFileWriter;

public class BeanFactoryTest {

	private BeanFactory beanFactory;

	@Before
	public void setUp() throws Exception {
		beanFactory = BeanFactory.INSTANCE;
	}

	@After
	public void tearDown() throws Exception {
		beanFactory = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public void getBeanInstanceByName_forNullInput_throwsIllegalArgumentException() {
		beanFactory.getBeanInstanceByName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getBeanInstanceByName_forEmptyInput_throwsIllegalArgumentException() {
		beanFactory.getBeanInstanceByName("");
	}

	@Test
	public void getBeanInstanceByName_forCsvFileType_returnXmlCsvWriterObject() {
		final FileWriter csvFileWriter = beanFactory.getBeanInstanceByName("csvFileWriter");
		assertNotNull(csvFileWriter);
		assertTrue(csvFileWriter instanceof CsvFileWriter);
		csvFileWriter.write("sample text");
	}

	@Test
	public void getBeanInstanceByName_forXmlFileType_returnXmlFileWriterObject() {
		final FileWriter xmlFileWriter = beanFactory.getBeanInstanceByName("xmlFileWriter");
		assertNotNull(xmlFileWriter);
		assertTrue(xmlFileWriter instanceof XmlFileWriter);
		xmlFileWriter.write("sample text");
	}

	@Test
	public void getBeanInstanceByName_forTextFileType_returnTextFileWriterObject() {
		final FileWriter textFileWriter = beanFactory.getBeanInstanceByName("textFileWriter");
		assertNotNull(textFileWriter);
		assertTrue(textFileWriter instanceof TextFileWriter);
		textFileWriter.write("sample text");
	}

}
