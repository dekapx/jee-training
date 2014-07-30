package com.ericsson.trainings.jee6.service.impl;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.entity.Contact;
import com.ericsson.trainings.jee6.service.ContactService;

@Stateless
@Local(ContactService.class)
public class ContactServiceImpl implements ContactService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactServiceImpl.class);

	@PersistenceContext(unitName = "mysql-persistence-unit")
	private EntityManager entityManager;

	@Override
	public Contact save(Contact contact) {
		entityManager.persist(contact);
		LOGGER.info("persisted contact entity [{}]...", contact);
		return contact;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<Contact> findAll() {
		final String query = "select o from Contact o";
		return this.entityManager.createQuery(query).getResultList();
	}
}
