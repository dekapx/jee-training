package com.ericsson.trainings.jaxb.unmarshaller;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.ericsson.trainings.jaxb.beans.Student;

public class JaxbUnmarshaller {
	public static void main(String[] args) {
		final File file = new File("src/main/resources/xml/student.xml");
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Student.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Student student = (Student) jaxbUnmarshaller.unmarshal(file);
			System.out.println(student.getId());
			System.out.println(student.getFirstName());
			System.out.println(student.getLastName());
			System.out.println(student.getMarks());
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
