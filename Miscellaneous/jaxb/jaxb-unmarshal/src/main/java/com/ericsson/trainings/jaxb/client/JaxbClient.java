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
		final Student student = JaxbUnmarshaller.unmarshall(Student.class, xmlFile);
		System.out.println(student.getId() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.getMarks());
	}

	public static void unmarshallContact() {
		final File xmlFile = new File("src/main/resources/xml/contact.xml");
		final Contact contact = JaxbUnmarshaller.unmarshall(Contact.class, xmlFile);
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
		final Department department = JaxbUnmarshaller.unmarshall(Department.class, xmlFile);
		System.out.println(department.getId() + " " + department.getName());

		final List<Employee> employees = department.getEmployee();
		for (Employee employee : employees) {
			System.out.println(employee.getFirstName() + " " + employee.getLastName() + " " + employee.getDesignation());
		}
	}
}
