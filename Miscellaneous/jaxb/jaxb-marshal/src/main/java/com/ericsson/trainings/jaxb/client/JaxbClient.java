package com.ericsson.trainings.jaxb.client;

import java.io.File;

import com.ericsson.trainings.jaxb.beans.Address;
import com.ericsson.trainings.jaxb.beans.Contact;
import com.ericsson.trainings.jaxb.beans.Student;
import com.ericsson.trainings.jaxb.marshaller.JaxbMarshaller;

public class JaxbClient {

	public static void main(String[] args) {
		marshallStudent();
		System.out.println("----------------------------------");
		masrhallContact();
		System.out.println("----------------------------------");
	}

	public static void masrhallContact() {
		final Contact contact = new Contact();
		contact.setFirstName("De");
		contact.setLastName("Kapx");
		contact.setEmail("dekapx@gmail.com");
		contact.setContactNumber(123456789);

		final Address address = new Address();
		address.setHouseNo("LM Ericsson Ltd.");
		address.setStreet("Ericsson Software Campus");
		address.setCity("Athlone");
		address.setZipCode("N37 PV44");
		address.setCounty("Westmeath");
		contact.setAddress(address);

		final File xmlFile = new File("src/main/resources/xml/contact.xml");
		JaxbMarshaller.marshall(contact, xmlFile);
	}

	public static void marshallStudent() {
		final Student student = new Student();
		student.setId(1L);
		student.setFirstName("De");
		student.setLastName("Kapx");
		student.setMarks(98.99);

		final File xmlFile = new File("src/main/resources/xml/student.xml");
		JaxbMarshaller.marshall(student, xmlFile);
	}

}
