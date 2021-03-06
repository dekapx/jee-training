package com.ericsson.trainings.jse.weld.cdi.beans;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named("csvFileWriter")
public class CsvFileWriter implements FileWriter {
	private static final Logger LOGGER = LoggerFactory.getLogger(CsvFileWriter.class);

	@Override
	public void write(String contents) {
		LOGGER.info("CsvFileWriter.write -> {}", contents);
	}

}
