package com.ericsson.trainings.jee6.jpa.many2many.dao.impl;

import java.util.Collection;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.jpa.generic.dao.AbstractJpaDao;
import com.ericsson.trainings.jee6.jpa.many2many.dao.StudentDao;
import com.ericsson.trainings.jee6.jpa.many2many.entity.Student;

@Named("studentDao")
public class StudentDaoImpl extends AbstractJpaDao<Student, Long> implements StudentDao<Student> {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentDaoImpl.class.getName());

	public StudentDaoImpl() {
		super(Student.class);
	}

	@Override
	public Student save(Student entity) {
		super.persist(entity);

		LOGGER.info("Persisted Student object with ID {} ", entity.getStudentId());
		return entity;
	}

	@Override
	public Student update(Student entity) {
		super.persist(entity);

		LOGGER.info("Updated Student object with ID {} ", entity.getStudentId());
		return entity;
	}

	@Override
	public Collection<Student> findAll() {
		return super.findAll();
	}

}
