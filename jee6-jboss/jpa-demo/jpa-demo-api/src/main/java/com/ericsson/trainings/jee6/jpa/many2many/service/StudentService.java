package com.ericsson.trainings.jee6.jpa.many2many.service;

import java.io.Serializable;
import java.util.Collection;

public interface StudentService<T extends Serializable> {
	T save(T entity);

	T update(T entity);

	Collection<T> findAll();
}
