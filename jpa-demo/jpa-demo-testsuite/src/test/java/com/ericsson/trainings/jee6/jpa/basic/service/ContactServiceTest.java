package com.ericsson.trainings.jee6.jpa.basic.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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

import com.ericsson.trainings.jee6.jpa.basic.dao.ContactDao;
import com.ericsson.trainings.jee6.jpa.basic.dao.impl.ContactDaoImpl;
import com.ericsson.trainings.jee6.jpa.basic.entity.Contact;
import com.ericsson.trainings.jee6.jpa.basic.service.impl.ContactServiceBean;
import com.ericsson.trainings.jee6.jpa.generic.dao.AbstractJpaDao;

@RunWith(Arquillian.class)
public class ContactServiceTest {
	@Deployment(name = "contact-test")
	public static Archive<?> createTestArchive() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "contact-test.war");

		war.addClass(ContactService.class);
		war.addClass(ContactServiceBean.class);

		war.addClass(AbstractJpaDao.class);
		war.addClass(ContactDao.class);
		war.addClass(ContactDaoImpl.class);

		war.addClass(Contact.class);

		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		war.addAsResource("META-INF/test-mysql-persistence.xml", "META-INF/persistence.xml");
		war.addAsWebInfResource("test-mysql-ds.xml");

		return war;
	}

	@EJB
	private ContactService<Contact> contactService;

	@Test
	@InSequence(1)
	public void test_insert_contact() throws Exception {

		final Collection<Contact> contacts = getContacts();
		for (Contact contact : contacts) {
			contact = contactService.save(contact);
			assertNotNull(contact);
			assertNotNull(contact.getId());
		}
	}

	private Collection<Contact> getContacts() {
		Collection<Contact> contacts = new ArrayList<>();

		Contact contact = new Contact();
		contact.setFirstName("Bruce");
		contact.setLastName("Lee");
		contact.setEmail("bruce@jeetkunedo.com");
		contacts.add(contact);

		return contacts;
	}

	@Test
	@InSequence(2)
	public void test_findAll_contacts() throws Exception {
		final Collection<Contact> contacts = contactService.findAll();

		assertNotNull(contacts);
		assertTrue(contacts.size() > 0);
	}
}
