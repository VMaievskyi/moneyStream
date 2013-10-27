package com.piramida.service.account.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.piramida.dao.account.AccountDao;
import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.service.account.AccountService;

public class DefaultAccountService implements AccountService {

    private static Logger LOG = LoggerFactory
	    .getLogger(DefaultAccountService.class);

    @Autowired
    private AccountDao accountDao;

    @Transactional
    public void createUserAccount(final Account account) {
	saveAccount(account);
	if (LOG.isDebugEnabled()) {
	    LOG.debug("account was created => {}", account);
	}
    }

    @Transactional
    public void updateUserAccount(final Account account) {
	saveAccount(account);
	if (LOG.isDebugEnabled()) {
	    LOG.debug("account was updated => {}", account);
	}
    }

    @Transactional
    public void activateUserAccount(final String userActivationString) {
	final Account unactivatedAccount = accountDao
		.findByActivationString(userActivationString);
	if (unactivatedAccount.getStatus() == ActivationStatus.PENDING) {
	    unactivatedAccount.setStatus(ActivationStatus.ACTIVE);
	    accountDao.save(unactivatedAccount);
	    if (LOG.isDebugEnabled()) {
		LOG.debug("account was activated => {}", unactivatedAccount);
	    }
	}
    }

    @Transactional
    public void deactivateAccount(final Account account) {
	if (account.getStatus() == ActivationStatus.ACTIVE) {
	    account.setStatus(ActivationStatus.PENDING);
	    accountDao.save(account);
	    if (LOG.isDebugEnabled()) {
		LOG.debug("account was deactivated => {}", account);
	    }
	}
    }

    @Transactional
    public Account findByEmail(final String email) {
	return accountDao.findByEmail(email);
    }

    private void saveAccount(final Account account) {
	accountDao.save(account);
    }

    public AccountDao getAccountDao() {
	return accountDao;
    }

    public void setAccountDao(final AccountDao accountDao) {
	this.accountDao = accountDao;
    }

}
