package com.ericsson.trainings.jse.weld.cdi.beans;

import javax.inject.Named;

@Named("xmlFileWriter")
public class XmlFileWriter implements FileWriter {

	@Override
	public void write(String contents) {
		System.out.println("XmlFileWriter :: " + contents);
	}

}
