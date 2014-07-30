package com.ericsson.trainings.jee6.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.trainings.jee6.entity.Contact;
import com.ericsson.trainings.jee6.service.impl.ContactServiceImpl;

@RunWith(Arquillian.class)
public class ContactServiceTest {
	@Deployment(name = "contact-test")
	public static Archive<?> createTestArchive() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "contact-test.war");

		war.addClass(ContactService.class);
		war.addClass(ContactServiceImpl.class);

		war.addClass(Contact.class);

		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		war.addAsResource("META-INF/test-mysql-persistence.xml", "META-INF/persistence.xml");
		war.addAsWebInfResource("test-mysql-ds.xml");

		return war;
	}

	@EJB
	private ContactService contactService;

	@Test
	@InSequence(1)
	public void test_insert_contact() throws Exception {
		final String firstName = "De";
		final String lastName = "Kapx";
		final String email = "dekapx@gmail.com";

		Contact contact = new Contact();
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setEmail(email);

		contact = contactService.save(contact);

		assertNotNull(contact);
		assertNotNull(contact.getId());
	}

	@Test
	@InSequence(2)
	public void test_findAll_contacts() throws Exception {
		final Collection<Contact> contacts = contactService.findAll();

		assertNotNull(contacts);
		assertTrue(contacts.size() > 0);
	}
}
