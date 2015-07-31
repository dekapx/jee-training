package com.ericsson.trainings.jee6.jpa.basic.dao;

import java.io.Serializable;
import java.util.Collection;

public interface ContactDao<T extends Serializable> {
	T save(T entity);

	Collection<T> findAll();
}
