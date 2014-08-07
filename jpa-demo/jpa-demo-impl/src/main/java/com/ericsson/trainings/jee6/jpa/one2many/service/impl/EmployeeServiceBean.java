package com.ericsson.trainings.jee6.jpa.one2many.service.impl;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ericsson.trainings.jee6.jpa.one2many.dao.EmployeeDao;
import com.ericsson.trainings.jee6.jpa.one2many.entity.Employee;
import com.ericsson.trainings.jee6.jpa.one2many.service.EmployeeService;

@Stateless
@Local(EmployeeService.class)
public class EmployeeServiceBean implements EmployeeService<Employee> {

	@Inject
	private EmployeeDao<Employee> employeeDao;

	@Override
	public Employee save(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	public Collection<Employee> findAll() {
		return employeeDao.findAll();
	}

}
