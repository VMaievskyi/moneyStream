package com.piramida.facade.impl;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.piramida.entity.Account;
import com.piramida.entity.EmailType;
import com.piramida.entity.dto.AccountDto;
import com.piramida.entity.mapper.factory.MapperFactory;
import com.piramida.entity.mapper.impl.AbstractDtoEntityMapper;
import com.piramida.facade.AccountFacade;
import com.piramida.service.account.AccountService;
import com.piramida.service.mail.MailService;
import com.piramida.service.security.HashGeneratorService;

public class DefaultAccountFacade implements AccountFacade {

    @Autowired
    private AccountService accountService;
    @Autowired
    @Qualifier(value = "securityStringGenerator")
    private HashGeneratorService hashGeneratorService;
    @Autowired
    @Qualifier(value = "mailService")
    private MailService mailService;
    @Autowired
    @Qualifier("mapperFactory")
    private MapperFactory mapperFactory;

    public void activateAccount(final String string) {
	Validate.notNull(string, "user activation string required");
	getAccountService().activateUserAccount(string);
    }

    public void createAccount(final AccountDto accountDto) {
	Validate.notNull(accountDto, "account cannot be null while creating");
	Validate.notNull(accountDto.getEmail(), "email field is mandatory");
	Validate.notNull(accountDto.getPassword(),
		"password field is mandatory");
	if (getAccountService().findByEmail(accountDto.getEmail()) == null) {
	    final AbstractDtoEntityMapper instance = mapperFactory
		    .createInstance(accountDto.getClass());
	    final Account account = (Account) instance.map(accountDto);
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

    public void updateAccount(final Account account) {
	Validate.notNull(account, "account cannot be null while update");
	Validate.notNull(account.getEmail(), "email field is mandatory");
	if (accountService.findByEmail(account.getEmail()) != null) {
	    accountService.updateUserAccount(account);
	}
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

    public MapperFactory getMapperFactory() {
	return mapperFactory;
    }

    public void setMapperFactory(final MapperFactory mapperFactory) {
	this.mapperFactory = mapperFactory;
    }

}
