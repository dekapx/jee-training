package com.ericsson.trainings.jse.weld.cdi.util;

import javax.inject.Inject;

import com.ericsson.trainings.jse.weld.cdi.beans.FileWriter;
import com.ericsson.trainings.jse.weld.cdi.producer.FileWriterTypes;
import com.ericsson.trainings.jse.weld.cdi.producer.WriterType;

public class FileWriterUtil {

	@Inject
	@WriterType(FileWriterTypes.CSV)
	private FileWriter fileWriter;

	public void writeToFile(final String contents) {
		fileWriter.write(contents);
	}
}
