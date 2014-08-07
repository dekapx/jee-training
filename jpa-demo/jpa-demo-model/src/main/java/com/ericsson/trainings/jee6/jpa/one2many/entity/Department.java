package com.ericsson.trainings.jee6.jpa.one2many.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENTS")
@SuppressWarnings("serial")
public class Department implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long deptId;

	@Column(name = "DEPT_NAME", length = 25, nullable = false)
	private String deptName;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private Collection<Employee> employees;

	public Department() {
		employees = new ArrayList<>();
	}

	public Department(String deptName) {
		this.deptName = deptName;
	}

	public Long getDeptId() {
		return deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public Collection<Employee> getEmployees() {
		return employees;
	}
}
