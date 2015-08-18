package com.ericsson.trainings.jaxb.marshaller;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.ericsson.trainings.jaxb.beans.Student;

public class JaxbMarshaller {
	public static void main(String[] args) {
		final Student student = new Student();
		student.setId(1L);
		student.setFirstName("De");
		student.setLastName("Kapx");
		student.setMarks(87.69);

		final File file = new File("src/main/resources/xml/student.xml");
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
			final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(student, file);
			jaxbMarshaller.marshal(student, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
