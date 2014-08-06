package com.ericsson.trainings.jee6.jpa.one2one.dao.impl;

import java.util.Collection;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.jpa.generic.dao.AbstractJpaDao;
import com.ericsson.trainings.jee6.jpa.one2one.dao.PersonDao;
import com.ericsson.trainings.jee6.jpa.one2one.entity.Person;

@Named("personDao")
public class PersonDaoImpl extends AbstractJpaDao<Person, Long> implements PersonDao<Person> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonDaoImpl.class.getName());

	public PersonDaoImpl() {
		super(Person.class);
	}

	@Override
	public Person save(Person entity) {
		super.persist(entity);

		LOGGER.info("Persisted Person object with ID {} ", entity.getId());
		return entity;
	}

	@Override
	public Collection<Person> findAll() {
		return super.findAll();
	}

}
