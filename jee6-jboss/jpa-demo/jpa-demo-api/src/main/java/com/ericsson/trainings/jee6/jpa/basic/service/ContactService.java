package com.ericsson.trainings.jee6.jpa.basic.service;

import java.io.Serializable;
import java.util.Collection;

public interface ContactService<T extends Serializable> {
	T save(T entity);

	Collection<T> findAll();
}
