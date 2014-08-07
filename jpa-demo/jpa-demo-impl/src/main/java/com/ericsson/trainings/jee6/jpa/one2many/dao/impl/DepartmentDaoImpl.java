package com.ericsson.trainings.jee6.jpa.one2many.dao.impl;

import java.util.Collection;

import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.jpa.generic.dao.AbstractJpaDao;
import com.ericsson.trainings.jee6.jpa.one2many.dao.DepartmentDao;
import com.ericsson.trainings.jee6.jpa.one2many.entity.Department;

@Named("departmentDao")
public class DepartmentDaoImpl extends AbstractJpaDao<Department, Long> implements DepartmentDao<Department> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoImpl.class.getName());

	public DepartmentDaoImpl() {
		super(Department.class);
	}

	@Override
	public Department save(Department entity) {
		super.persist(entity);

		LOGGER.info("Persisted Department object with ID {} ", entity.getDeptId());
		return entity;
	}

	@Override
	public Collection<Department> findAll() {
		return super.findAll();
	}

	@Override
	public Department findByDeptName(String deptName) {
		LOGGER.info("Find department by dept name [{}]", deptName);

		final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);

		final Root<Department> department = criteriaQuery.from(Department.class);
		criteriaQuery.where(criteriaBuilder.equal(department.get("deptName"), deptName));

		final TypedQuery<Department> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList().iterator().next();
	}
}
