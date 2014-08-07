package com.ericsson.trainings.jee6.jpa.one2many.dao.impl;

import java.util.Collection;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.jpa.generic.dao.AbstractJpaDao;
import com.ericsson.trainings.jee6.jpa.one2many.dao.EmployeeDao;
import com.ericsson.trainings.jee6.jpa.one2many.entity.Employee;

@Named("employeeDao")
public class EmployeeDaoImpl extends AbstractJpaDao<Employee, Long> implements EmployeeDao<Employee> {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class.getName());

	public EmployeeDaoImpl() {
		super(Employee.class);
	}

	@Override
	public Employee save(Employee entity) {
		super.persist(entity);

		LOGGER.info("Persisted Employee object with ID {} ", entity.getEmpId());
		return entity;
	}

	@Override
	public Collection<Employee> findAll() {
		return super.findAll();
	}

}
