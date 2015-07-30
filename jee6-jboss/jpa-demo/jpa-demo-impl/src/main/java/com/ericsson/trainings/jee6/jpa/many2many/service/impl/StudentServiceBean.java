package com.ericsson.trainings.jee6.jpa.many2many.service.impl;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import com.ericsson.trainings.jee6.jpa.many2many.dao.StudentDao;
import com.ericsson.trainings.jee6.jpa.many2many.entity.Student;
import com.ericsson.trainings.jee6.jpa.many2many.service.StudentService;

@Stateless
@Local(StudentService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StudentServiceBean implements StudentService<Student> {

	@Inject
	private StudentDao<Student> studentDao;

	@Override
	public Student save(Student student) {
		return studentDao.save(student);
	}

	@Override
	public Student update(Student student) {
		return studentDao.update(student);
	}

	@Override
	public Collection<Student> findAll() {
		return studentDao.findAll();
	}

}
