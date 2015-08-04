package com.ericsson.trainings.jee.weld.cdi.beans;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named("textFileWriter")
public class TextFileWriter implements FileWriter {
	private static final Logger LOGGER = LoggerFactory.getLogger(TextFileWriter.class);

	@Override
	public void write(String contents) {
		LOGGER.info("TextFileWriter :: " + contents);
	}

}
