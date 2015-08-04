package com.ericsson.trainings.jse.weld.cdi.util;

import javax.inject.Inject;

import com.ericsson.trainings.jse.weld.cdi.beans.FileWriter;

public class FileWriterUtil {

	@Inject
	@FileWriterType(FileWriterTypes.CSV)
	private FileWriter csvFileWriter;

	public void writeCsvFile(final String contents) {
		csvFileWriter.write(contents);
	}
}
