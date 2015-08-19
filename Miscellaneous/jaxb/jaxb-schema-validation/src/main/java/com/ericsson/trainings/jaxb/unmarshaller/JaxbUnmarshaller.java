package com.ericsson.trainings.jaxb.unmarshaller;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.ericsson.trainings.jaxb.handler.SchemaValidationEventHandler;

public class JaxbUnmarshaller {
	@SuppressWarnings("unchecked")
	public static <T> T unmarshallAndValidate(final Class<T> clazz, final File xmlFile, final File xsdFile) {
		try {
			final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			final Schema schema = schemaFactory.newSchema(new StreamSource(xsdFile));

			final JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setSchema(schema);
			jaxbUnmarshaller.setEventHandler(new SchemaValidationEventHandler());
			return (T) jaxbUnmarshaller.unmarshal(xmlFile);
		} catch (JAXBException | SAXException e) {
			e.printStackTrace();
		}
		return null;
	}
}
