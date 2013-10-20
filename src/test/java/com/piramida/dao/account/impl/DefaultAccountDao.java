package com.piramida.dao.account.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.piramida.dao.account.AccountDao;
import com.piramida.entity.Account;

public class DefaultAccountDao implements AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Account> findAll() {
	final Session currentSession = sessionFactory.openSession();
	final List list = currentSession.createQuery("from Account").list();
	return list;
    }

    public void deleteAll() {
	final Session currentSession = sessionFactory.openSession();
	currentSession.createQuery("DELETE FROM Account").executeUpdate();
    }

    public void create(final Account account) {
	final Session currentSession = sessionFactory.openSession();
	currentSession.persist(account);
    }

}
