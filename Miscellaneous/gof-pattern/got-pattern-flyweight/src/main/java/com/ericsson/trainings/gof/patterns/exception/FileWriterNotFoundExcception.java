package com.ericsson.trainings.gof.patterns.exception;

@SuppressWarnings("serial")
public class FileWriterNotFoundExcception extends Exception {

	public FileWriterNotFoundExcception(String message, Exception cause) {
		super(message, cause);
	}

}
