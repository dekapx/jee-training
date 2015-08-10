package com.ericsson.trainings.jse.service.impl;

import com.ericsson.trainings.jse.service.FileWriter;

public class DummyFileWriter implements FileWriter {

	@Override
	public void write(String contents) {
		System.out.println("DummyFileWriter.write -> " + contents);
	}

}
