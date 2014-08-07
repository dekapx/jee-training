package com.ericsson.trainings.jee6.jpa.generic.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Abstract JPA based DAO class consisting of common data access operations.
 * 
 * @author KAPIL
 * 
 * @param <T>
 *            Type of persistence object.
 * @param <PK>
 *            Serializable primary key for entity
 * 
 */
public abstract class AbstractJpaDao<T extends Serializable, PK extends Serializable> {

	@PersistenceContext(unitName = "mysql-persistence-unit")
	private EntityManager entityManager;

	private Class<T> persistentClass;

	protected AbstractJpaDao(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void persist(final T entity) {
		entityManager.persist(entity);
	}

	public void update(final T entity) {
		entityManager.merge(entity);
	}

	public T find(final PK pk, final Class<T> t) {
		return entityManager.find(t, pk);
	}

	public void delete(final T entity) {
		entityManager.remove(entity);
	}

	@SuppressWarnings("unchecked")
	public Collection<T> findAll() {
		return this.entityManager.createQuery("select obj from " + this.persistentClass.getName() + " obj").getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<T> findAll(final int page, final int pageSize) {
		final TypedQuery<T> query = (TypedQuery<T>) entityManager.createQuery("select obj from " + this.persistentClass.getName() + " obj");

		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();
	}
}
