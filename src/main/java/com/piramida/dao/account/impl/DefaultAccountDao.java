package com.piramida.dao.account.impl;

import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.piramida.dao.AbstractGenegicDao;
import com.piramida.dao.account.AccountDao;
import com.piramida.entity.Account;
import com.piramida.entity.Wallet;

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
    public Account findById(final Integer accountId) {
	final Session currentSession = getSessionFactory().getCurrentSession();
	final Query searchQuery = currentSession
		.createQuery("from Account where id=:id");
	searchQuery.setParameter("id", accountId);
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

    @Override
    public void save(final Account entity) {
	final Set<Wallet> wallets = entity.getWallets();
	if (wallets != null) {
	    entity.setWallets(null);
	    super.save(entity);
	    for (final Wallet wallet : wallets) {
		wallet.setOwner(entity);
	    }
	    entity.setWallets(wallets);
	}
	super.save(entity);
    }

}
