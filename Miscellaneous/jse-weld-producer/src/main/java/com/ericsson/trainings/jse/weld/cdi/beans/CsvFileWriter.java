package com.ericsson.trainings.jse.weld.cdi.beans;

public class CsvFileWriter implements FileWriter {

	@Override
	public void write(String contents) {
		System.out.println("CsvFileWriter :: " + contents);
	}

}
