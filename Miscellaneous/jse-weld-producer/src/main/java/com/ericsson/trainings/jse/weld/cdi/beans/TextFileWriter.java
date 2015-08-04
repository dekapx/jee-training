package com.ericsson.trainings.jse.weld.cdi.beans;

public class TextFileWriter implements FileWriter {

	@Override
	public void write(String contents) {
		System.out.println("TextFileWriter :: " + contents);
	}

}
