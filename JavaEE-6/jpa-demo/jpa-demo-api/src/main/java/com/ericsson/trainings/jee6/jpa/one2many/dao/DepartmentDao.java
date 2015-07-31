package com.ericsson.trainings.jee6.jpa.one2many.dao;

import java.io.Serializable;
import java.util.Collection;

public interface DepartmentDao<T extends Serializable> {
	T save(T entity);

	Collection<T> findAll();

	T findByDeptName(String deptName);
}
