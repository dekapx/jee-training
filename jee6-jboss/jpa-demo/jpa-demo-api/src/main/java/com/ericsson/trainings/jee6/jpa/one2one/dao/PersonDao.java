package com.ericsson.trainings.jee6.jpa.one2one.dao;

import java.io.Serializable;
import java.util.Collection;

public interface PersonDao<T extends Serializable> {
	T save(T entity);

	Collection<T> findAll();

}
