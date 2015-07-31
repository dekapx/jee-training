package com.ericsson.trainings.jee6.jpa.one2one.service;

import java.io.Serializable;
import java.util.Collection;

public interface PersonService<T extends Serializable> {
	T save(T entity);

	Collection<T> findAll();
}
