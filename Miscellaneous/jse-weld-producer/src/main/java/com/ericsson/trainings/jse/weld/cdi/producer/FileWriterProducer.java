package com.ericsson.trainings.jse.weld.cdi.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.ericsson.trainings.jse.weld.cdi.beans.CsvFileWriter;
import com.ericsson.trainings.jse.weld.cdi.beans.FileWriter;
import com.ericsson.trainings.jse.weld.cdi.beans.TextFileWriter;
import com.ericsson.trainings.jse.weld.cdi.beans.XmlFileWriter;

@ApplicationScoped
public class FileWriterProducer {

	@Produces
	@WriterType(FileWriterTypes.CSV)
	private FileWriter getCsvFileWriter() {
		return new CsvFileWriter();
	}

	@Produces
	@WriterType(FileWriterTypes.TEXT)
	private FileWriter getTextFileWriter() {
		return new TextFileWriter();
	}

	@Produces
	@WriterType(FileWriterTypes.XML)
	private FileWriter getXmlFileWriter() {
		return new XmlFileWriter();
	}
}
