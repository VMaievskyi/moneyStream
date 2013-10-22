package com.piramida.facade.impl;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import com.piramida.entity.Account;
import com.piramida.entity.EmailType;
import com.piramida.facade.AccountFacade;
import com.piramida.service.MailService;
import com.piramida.service.account.AccountService;
import com.piramida.service.security.HashGeneratorService;

public class DefaultAccountFacade implements AccountFacade {

    @Autowired
    private AccountService accountService;
    @Autowired
    private HashGeneratorService hashGeneratorService;
    @Autowired
    private MailService mailService;

    public void activateAccount(final String string) {
	Validate.notNull(string, "user activation string required");
	getAccountService().activateUserAccount(string);
    }

    public void createAccount(final Account account) {
	Validate.notNull(account, "account cannot be null while creating");
	Validate.notNull(account.getEmail(), "email field is mandatory");
	Validate.notNull(account.getPassword(), "password field is mandatory");
	if (getAccountService().findByEmail(account.getEmail()) == null) {
	    createValidetedAccount(account);
	    mailService.sendEmail(EmailType.REGISTRATION, account);
	} else {
	    throw new IllegalArgumentException("User with this email exists");
	}
    }

    public void deactivateAccount(final Account account) {
	Validate.notNull(account, "account cannot be null while unactivation");
	accountService.deactivateAccount(account);

    }

    private void createValidetedAccount(final Account account) {
	final String activationString = getHashGeneratorService()
		.generateValue(account.getEmail());
	account.setActivationString(activationString);
	getAccountService().createUserAccount(account);
    }

    public AccountService getAccountService() {
	return accountService;
    }

    public void setAccountService(final AccountService accountService) {
	this.accountService = accountService;
    }

    public HashGeneratorService getHashGeneratorService() {
	return hashGeneratorService;
    }

    public void setHashGeneratorService(
	    final HashGeneratorService hashGeneratorService) {
	this.hashGeneratorService = hashGeneratorService;
    }

    public MailService getMailService() {
	return mailService;
    }

    public void setMailService(final MailService mailService) {
	this.mailService = mailService;
    }

}
