package com.ericsson.trainings.jee6.jpa.many2many.service;

import java.io.Serializable;
import java.util.Collection;

public interface CourseService<T extends Serializable> {
	T save(T entity);

	Collection<T> findAll();

	T findByCourseName(String courseName);
}
