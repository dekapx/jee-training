package com.ericsson.trainings.jee6.jpa.many2many.service.impl;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import com.ericsson.trainings.jee6.jpa.many2many.dao.CourseDao;
import com.ericsson.trainings.jee6.jpa.many2many.entity.Course;
import com.ericsson.trainings.jee6.jpa.many2many.service.CourseService;

@Stateless
@Local(CourseService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CourseServiceBean implements CourseService<Course> {

	@Inject
	private CourseDao<Course> courseDao;

	@Override
	public Course save(Course Course) {
		return courseDao.save(Course);
	}

	@Override
	public Collection<Course> findAll() {
		return courseDao.findAll();
	}

	@Override
	public Course findByCourseName(String courseName) {
		return courseDao.findByCourseName(courseName);
	}
}
