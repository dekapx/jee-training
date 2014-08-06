package com.ericsson.trainings.jee6.jpa.basic.service.impl;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ericsson.trainings.jee6.jpa.basic.dao.ContactDao;
import com.ericsson.trainings.jee6.jpa.basic.entity.Contact;
import com.ericsson.trainings.jee6.jpa.basic.service.ContactService;

@Stateless
@Local(ContactService.class)
public class ContactServiceBean implements ContactService<Contact> {

	@Inject
	private ContactDao<Contact> contactDao;

	@Override
	public Contact save(Contact contact) {
		return contactDao.save(contact);
	}

	@Override
	public Collection<Contact> findAll() {
		return contactDao.findAll();
	}

}
