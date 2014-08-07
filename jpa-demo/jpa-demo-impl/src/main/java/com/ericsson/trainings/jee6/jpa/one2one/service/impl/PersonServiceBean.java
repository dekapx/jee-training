package com.ericsson.trainings.jee6.jpa.one2one.service.impl;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ericsson.trainings.jee6.jpa.one2one.dao.PersonDao;
import com.ericsson.trainings.jee6.jpa.one2one.entity.Person;
import com.ericsson.trainings.jee6.jpa.one2one.service.PersonService;

@Stateless
@Local(PersonService.class)
public class PersonServiceBean implements PersonService<Person> {

	@Inject
	private PersonDao<Person> personDao;

	@Override
	public Person save(Person person) {
		return personDao.save(person);
	}

	@Override
	public Collection<Person> findAll() {
		return personDao.findAll();
	}
}
