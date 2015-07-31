package com.ericsson.trainings.gof.patterns.beans;


public class XmlFileWriter implements FileWriter {

	@Override
	public void write(String contents) {
		System.out.println("XmlFileWriter :: " + contents);
	}

}
