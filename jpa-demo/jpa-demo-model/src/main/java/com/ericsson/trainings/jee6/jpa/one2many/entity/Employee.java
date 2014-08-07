package com.ericsson.trainings.jee6.jpa.one2many.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEES")
@SuppressWarnings("serial")
public class Employee implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long empId;

	@Column(name = "FIRST_NAME", length = 25, nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", length = 25, nullable = false)
	private String lastName;

	@Column(name = "EMAIL", length = 45, nullable = false)
	private String email;

	@ManyToOne
	private Department department;

	public Employee() {
	}

	public Employee(String firstName, String lastName, String email, Department department) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
	}

	public Long getEmpId() {
		return empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Department getDepartment() {
		return department;
	}

}
