package com.ericsson.trainings.jee6.jpa.many2many.dao;

import java.io.Serializable;
import java.util.Collection;

public interface CourseDao<T extends Serializable> {
	T save(T entity);

	Collection<T> findAll();

	T findByCourseName(String courseName);
}
