package com.ericsson.trainings.jse.weld.cdi.beans;

import javax.inject.Named;

@Named("csvFileWriter")
public class CsvFileWriter implements FileWriter {

	@Override
	public void write(String contents) {
		System.out.println("CsvFileWriter :: " + contents);
	}

}
