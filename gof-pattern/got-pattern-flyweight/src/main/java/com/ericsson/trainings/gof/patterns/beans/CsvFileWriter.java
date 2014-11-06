package com.ericsson.trainings.gof.patterns.beans;


public class CsvFileWriter implements FileWriter {

	@Override
	public void write(String contents) {
		System.out.println("CsvFileWriter :: " + contents);
	}

}
