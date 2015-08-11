package com.ericsson.trainings.jse.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jse.service.FileWriter;

public class DummyFileWriter implements FileWriter {
	private static final Logger LOGGER = LoggerFactory.getLogger(DummyFileWriter.class);

	@Override
	public void write(String contents) {
		LOGGER.info("DummyFileWriter.write -> {}", contents);
	}

}
