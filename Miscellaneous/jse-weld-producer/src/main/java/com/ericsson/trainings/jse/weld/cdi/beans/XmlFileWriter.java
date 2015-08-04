package com.ericsson.trainings.jse.weld.cdi.beans;

public class XmlFileWriter implements FileWriter {

	@Override
	public void write(String contents) {
		System.out.println("XmlFileWriter :: " + contents);
	}

}
