package com.ericsson.trainings.jee6.jpa.basic.dao.impl;

import java.util.Collection;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.jpa.basic.dao.ContactDao;
import com.ericsson.trainings.jee6.jpa.basic.entity.Contact;
import com.ericsson.trainings.jee6.jpa.generic.dao.AbstractJpaDao;

@Named("contactDao")
public class ContactDaoImpl extends AbstractJpaDao<Contact, Long> implements ContactDao<Contact> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactDaoImpl.class.getName());

	public ContactDaoImpl() {
		super(Contact.class);
	}

	@Override
	public Contact save(Contact entity) {
		super.persist(entity);

		LOGGER.info("Persisted Contact object with ID {} ", entity.getId());
		return entity;
	}

	@Override
	public Collection<Contact> findAll() {
		return super.findAll();
	}
}
