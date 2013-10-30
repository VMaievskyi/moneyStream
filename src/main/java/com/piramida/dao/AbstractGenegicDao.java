package com.piramida.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractGenegicDao<T> implements GenericDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    protected abstract String getEntityName();

    protected String findAll = "from " + getEntityName();

    public List<T> findAll() {
	final Session currentSession = sessionFactory.getCurrentSession();
	return currentSession.createQuery(findAll).list();
    }

    public void deleteAll() {
	final Session currentSession = sessionFactory.getCurrentSession();
	currentSession.createQuery("DELETE FROM " + getEntityName())
		.executeUpdate();
    }

    public void save(final T entity) {
	final Session currentSession = sessionFactory.getCurrentSession();
	currentSession.saveOrUpdate(entity);
    }

    public void delete(final T entity) {
	final Session currentSession = sessionFactory.getCurrentSession();
	currentSession.delete(entity);
    }

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(final SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

}
