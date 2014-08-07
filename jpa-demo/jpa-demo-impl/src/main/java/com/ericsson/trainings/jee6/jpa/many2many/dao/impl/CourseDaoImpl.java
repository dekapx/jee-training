package com.ericsson.trainings.jee6.jpa.many2many.dao.impl;

import java.util.Collection;

import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.jpa.generic.dao.AbstractJpaDao;
import com.ericsson.trainings.jee6.jpa.many2many.dao.CourseDao;
import com.ericsson.trainings.jee6.jpa.many2many.entity.Course;

@Named("courseDao")
public class CourseDaoImpl extends AbstractJpaDao<Course, Long> implements CourseDao<Course> {
	private static final Logger LOGGER = LoggerFactory.getLogger(CourseDaoImpl.class.getName());

	public CourseDaoImpl() {
		super(Course.class);
	}

	@Override
	public Course save(Course entity) {
		super.persist(entity);

		LOGGER.info("Persisted Course object with ID {} ", entity.getCourseId());
		return entity;
	}

	@Override
	public Collection<Course> findAll() {
		return super.findAll();
	}

	@Override
	public Course findByCourseName(String courseName) {
		LOGGER.info("Find course by course name [{}]", courseName);

		final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

		final Root<Course> course = criteriaQuery.from(Course.class);
		criteriaQuery.where(criteriaBuilder.equal(course.get("courseName"), courseName));

		final TypedQuery<Course> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList().iterator().next();
	}

}
