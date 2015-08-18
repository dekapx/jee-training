package com.ericsson.trainings.jaxb.marshaller;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JaxbMarshaller {
	public static <T> void marshall(final T object, final File xmlFile) {
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
			final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(object, xmlFile);
			jaxbMarshaller.marshal(object, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
