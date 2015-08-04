package com.ericsson.trainings.jse.weld.cdi.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.ericsson.trainings.jse.weld.cdi.beans.CsvFileWriter;
import com.ericsson.trainings.jse.weld.cdi.beans.FileWriter;
import com.ericsson.trainings.jse.weld.cdi.beans.TextFileWriter;
import com.ericsson.trainings.jse.weld.cdi.beans.XmlFileWriter;

@ApplicationScoped
public class FileWriterFactory {

	@Produces
	@FileWriterType(FileWriterTypes.CSV)
	private FileWriter getCsvFileWriter() {
		return new CsvFileWriter();
	}

	@Produces
	@FileWriterType(FileWriterTypes.TEXT)
	private FileWriter getTextFileWriter() {
		return new TextFileWriter();
	}

	@Produces
	@FileWriterType(FileWriterTypes.XML)
	private FileWriter getXmlFileWriter() {
		return new XmlFileWriter();
	}
}
