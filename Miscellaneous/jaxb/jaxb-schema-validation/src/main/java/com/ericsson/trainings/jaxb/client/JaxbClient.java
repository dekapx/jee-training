package com.ericsson.trainings.jaxb.client;

import java.io.File;
import java.util.List;

import com.ericsson.trainings.jaxb.beans.Address;
import com.ericsson.trainings.jaxb.beans.Contact;
import com.ericsson.trainings.jaxb.beans.Department;
import com.ericsson.trainings.jaxb.beans.Employee;
import com.ericsson.trainings.jaxb.beans.Student;
import com.ericsson.trainings.jaxb.unmarshaller.JaxbUnmarshaller;

public class JaxbClient {
	public static void main(String[] args) {
		unmarshallStudent();
		System.out.println("----------------------------------");
		unmarshallContact();
		System.out.println("----------------------------------");
		unmarshallDeptEmployee();
	}

	public static void unmarshallStudent() {
		final File xmlFile = new File("src/main/resources/xml/student.xml");
		final File xsdFile = new File("src/main/resources/xsd/student.xsd");
		final Student student = JaxbUnmarshaller.unmarshallAndValidate(Student.class, xmlFile, xsdFile);
		System.out.println(student.getId() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.getMarks());
	}

	public static void unmarshallContact() {
		final File xmlFile = new File("src/main/resources/xml/contact.xml");
		final File xsdFile = new File("src/main/resources/xsd/contact.xsd");
		final Contact contact = JaxbUnmarshaller.unmarshallAndValidate(Contact.class, xmlFile, xsdFile);
		final Address address = contact.getAddress();
		System.out.println(contact.getFirstName() + " " + contact.getLastName() + " " + contact.getEmail() + " " + contact.getContactNumber());
		System.out.println(address.getHouseNo());
		System.out.println(address.getStreet());
		System.out.println(address.getCity());
		System.out.println(address.getZipCode());
		System.out.println(address.getCounty());
	}

	public static void unmarshallDeptEmployee() {
		final File xmlFile = new File("src/main/resources/xml/dept-emp.xml");
		final File xsdFile = new File("src/main/resources/xsd/dept-emp.xsd");
		final Department department = JaxbUnmarshaller.unmarshallAndValidate(Department.class, xmlFile, xsdFile);
		System.out.println(department.getId() + " " + department.getName());

		final List<Employee> employees = department.getEmployee();
		for (Employee employee : employees) {
			System.out.println(employee.getFirstName() + " " + employee.getLastName() + " " + employee.getDesignation());
		}
	}
}
