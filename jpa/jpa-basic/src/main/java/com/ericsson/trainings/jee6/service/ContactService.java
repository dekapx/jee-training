package com.ericsson.trainings.jee6.service;

import java.util.Collection;

import com.ericsson.trainings.jee6.entity.Contact;

public interface ContactService {
	Contact save(Contact contact);

	Collection<Contact> findAll();
}
