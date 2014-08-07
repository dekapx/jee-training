package com.ericsson.trainings.jee6.jpa.one2many.service.impl;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ericsson.trainings.jee6.jpa.one2many.dao.DepartmentDao;
import com.ericsson.trainings.jee6.jpa.one2many.entity.Department;
import com.ericsson.trainings.jee6.jpa.one2many.service.DepartmentService;

@Stateless
@Local(DepartmentService.class)
public class DepartmentServiceBean implements DepartmentService<Department> {

	@Inject
	private DepartmentDao<Department> departmentDao;

	@Override
	public Department save(Department entity) {
		return departmentDao.save(entity);
	}

	@Override
	public Collection<Department> findAll() {
		return departmentDao.findAll();
	}

	@Override
	public Department findByDeptName(String deptName) {
		return departmentDao.findByDeptName(deptName);
	}

}
