package com.ericsson.trainings.jaxb.unmarshaller;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JaxbUnmarshaller {
	@SuppressWarnings("unchecked")
	public static <T> T unmarshall(final Class<T> clazz, final File outputFile) {
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (T) jaxbUnmarshaller.unmarshal(outputFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}
