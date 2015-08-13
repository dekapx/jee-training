package com.ericsson.trainings.jse.weld.cdi.beans;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named("xmlFileWriter")
public class XmlFileWriter implements FileWriter {
	private static final Logger LOGGER = LoggerFactory.getLogger(XmlFileWriter.class);

	@Override
	public void write(String contents) {
		LOGGER.info("XmlFileWriter.write -> {}", contents);
	}

}
