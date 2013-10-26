package com.piramida.dao.account.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.piramida.dao.AbstractGenegicDao;
import com.piramida.dao.account.AccountDao;
import com.piramida.entity.Account;

public class DefaultAccountDao extends AbstractGenegicDao<Account> implements
	AccountDao {

    public Account findByEmail(final String email) {
	final Session currentSession = getSessionFactory().getCurrentSession();
	final Query searchQuery = currentSession
		.createQuery("from Account where email=:email");
	searchQuery.setParameter("email", email);

	return (Account) searchQuery.uniqueResult();
    }

    @Override
    protected String getEntityName() {
	return "Account";
    }

    public Account findByActivationString(final String activationString) {
	final Session currentSession = getSessionFactory().getCurrentSession();
	return (Account) currentSession.createQuery(
		"from Account where activationString= '" + activationString
			+ "'").uniqueResult();
    }

}
