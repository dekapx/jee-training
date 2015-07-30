package com.ericsson.trainings.jee6.jpa.one2one.service;

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

import com.ericsson.trainings.jee6.jpa.generic.dao.AbstractJpaDao;
import com.ericsson.trainings.jee6.jpa.one2one.dao.PersonDao;
import com.ericsson.trainings.jee6.jpa.one2one.dao.impl.PersonDaoImpl;
import com.ericsson.trainings.jee6.jpa.one2one.entity.Address;
import com.ericsson.trainings.jee6.jpa.one2one.entity.Person;
import com.ericsson.trainings.jee6.jpa.one2one.service.impl.PersonServiceBean;

@RunWith(Arquillian.class)
public class PersonServiceTest {

	@Deployment(name = "person-test")
	public static Archive<?> createTestArchive() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "person-test.war");

		war.addClass(PersonService.class);
		war.addClass(PersonServiceBean.class);

		war.addClass(AbstractJpaDao.class);
		war.addClass(PersonDao.class);
		war.addClass(PersonDaoImpl.class);

		war.addClass(Person.class);
		war.addClass(Address.class);

		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		war.addAsResource("META-INF/test-mysql-persistence.xml", "META-INF/persistence.xml");
		war.addAsWebInfResource("test-mysql-ds.xml");

		return war;
	}

	@EJB
	private PersonService<Person> personService;

	@Test
	@InSequence(1)
	public void test_insert_person() throws Exception {

		final Collection<Person> persons = getPersons();
		for (Person person : persons) {
			person = personService.save(person);
			assertNotNull(person);
			assertNotNull(person.getId());
		}
	}

	private Collection<Person> getPersons() {
		Collection<Person> persons = new ArrayList<>();

		Address address = new Address("1830", "Gateway Drive", "San Mateo", "CA", "94403");
		Person person = new Person("Aditi", "Singh", "aditi@gmail.com", address);
		persons.add(person);

		return persons;
	}

	@Test
	@InSequence(2)
	public void test_findAll_persons() throws Exception {
		final Collection<Person> persons = personService.findAll();

		assertNotNull(persons);
		assertTrue(persons.size() > 0);

		for (Person person : persons) {
			assertNotNull(person.getId());
			assertNotNull(person.getAddress());
			assertNotNull(person.getAddress().getId());
		}
	}
}
