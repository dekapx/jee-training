package com.ericsson.trainings.jee6.jpa.one2one.service.impl;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import com.ericsson.trainings.jee6.jpa.one2one.dao.PersonDao;
import com.ericsson.trainings.jee6.jpa.one2one.entity.Person;
import com.ericsson.trainings.jee6.jpa.one2one.service.PersonService;

@Stateless
@Local(PersonService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PersonServiceBean implements PersonService<Person> {

	@Inject
	private PersonDao<Person> personDao;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Person save(Person person) {
		return personDao.save(person);
	}

	@Override
	public Collection<Person> findAll() {
		return personDao.findAll();
	}
}
