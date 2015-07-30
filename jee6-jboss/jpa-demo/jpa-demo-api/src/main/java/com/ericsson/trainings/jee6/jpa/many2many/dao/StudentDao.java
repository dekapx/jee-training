package com.ericsson.trainings.jee6.jpa.many2many.dao;

import java.io.Serializable;
import java.util.Collection;

public interface StudentDao<T extends Serializable> {
	T save(T entity);

	T update(T entity);

	Collection<T> findAll();
}
