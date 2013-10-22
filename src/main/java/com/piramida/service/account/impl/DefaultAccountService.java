package com.piramida.service.account.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.piramida.dao.account.AccountDao;
import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.service.account.AccountService;

public class DefaultAccountService implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public void createUserAccount(final Account account) {
	accountDao.save(account);
    }

    public void activateUserAccount(final String userActivationString) {
	final Account unactivatedAccount = accountDao
		.findByActivationString(userActivationString);
	if (unactivatedAccount.getStatus() == ActivationStatus.PENDING) {
	    unactivatedAccount.setStatus(ActivationStatus.ACTIVE);
	    accountDao.save(unactivatedAccount);
	}
    }

    public void deactivateAccount(final Account account) {
	if (account.getStatus() == ActivationStatus.ACTIVE) {
	    account.setStatus(ActivationStatus.PENDING);
	    accountDao.save(account);
	}
    }

    public AccountDao getAccountDao() {
	return accountDao;
    }

    public void setAccountDao(final AccountDao accountDao) {
	this.accountDao = accountDao;
    }

}
