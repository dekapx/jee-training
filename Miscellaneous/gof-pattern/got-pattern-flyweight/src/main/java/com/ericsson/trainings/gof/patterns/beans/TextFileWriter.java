package com.ericsson.trainings.gof.patterns.beans;


public class TextFileWriter implements FileWriter {

	@Override
	public void write(String contents) {
		System.out.println("TextFileWriter :: " + contents);
	}

}
