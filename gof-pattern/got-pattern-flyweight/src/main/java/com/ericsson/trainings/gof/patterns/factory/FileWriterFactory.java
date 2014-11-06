package com.ericsson.trainings.gof.patterns.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ericsson.trainings.gof.patterns.beans.FileWriter;
import com.ericsson.trainings.gof.patterns.exception.FileWriterNotFoundExcception;

public enum FileWriterFactory {
	INSTANCE;

	private Map<String, FileWriter> fileWriterObjectPool = new ConcurrentHashMap<>();

	public static FileWriterFactory getInstance() {
		return INSTANCE;
	}

	public FileWriter getFileWriter(final String className) throws FileWriterNotFoundExcception {
		FileWriter fileWriter = fileWriterObjectPool.get(className);

		if (fileWriter == null) {
			try {
				fileWriter = (FileWriter) Class.forName(className).newInstance();
				fileWriterObjectPool.put(className, fileWriter);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				throw new FileWriterNotFoundExcception("Unable to find the implemnation for class [ " + className + " ]. Please check the class name.", e);
			}
		}

		return fileWriter;
	}
}
