package com.piramida.facade.account.impl;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.UserRoles;
import com.piramida.entity.dto.AccountDto;
import com.piramida.entity.mapper.factory.MapperFactory;
import com.piramida.entity.mapper.impl.AbstractDtoEntityMapper;
import com.piramida.facade.account.AccountFacade;
import com.piramida.service.account.AccountService;
import com.piramida.service.mail.impl.IMailSender;
import com.piramida.service.security.HashGeneratorService;

public class AccountFacadeImpl implements AccountFacade {

    @Autowired
    private AccountService accountService;
    @Autowired
    @Qualifier(value = "securityStringGenerator")
    private HashGeneratorService hashGeneratorService;
    @Autowired
    @Qualifier("mapperFactory")
    private MapperFactory mapperFactory;
    @Autowired
    private IMailSender accountOperationInformer;

    @Override
    public void activateAccount(final String string) {
	Validate.notNull(string, "user activation string required");
	getAccountService().activateUserAccount(string);
    }

    @Override
    public Account createAccount(final AccountDto accountDto) {
	Validate.notNull(accountDto, "account cannot be null while creating");
	Validate.notNull(accountDto.getEmail(), "email field is mandatory");
	Validate.notNull(accountDto.getPassword(),
		"password field is mandatory");

	if (getAccountService().findByEmail(accountDto.getEmail()) == null) {
	    final AbstractDtoEntityMapper instance = mapperFactory
		    .createInstance(accountDto.getClass());

	    final Account account = (Account) instance.map(accountDto);
	    createValidetedAccount(account);
	    accountOperationInformer.sendEmail(account);
	    return account;
	} else {
	    throw new IllegalArgumentException("User with this email exists");
	}
    }

    private void createValidetedAccount(final Account account) {
	formValidatedUserAccount(account);
	getAccountService().createUserAccount(account);
    }

    private void formValidatedUserAccount(final Account account) {
	String activationString = getHashGeneratorService().generateValue(
		account.getEmail());
	activationString = makeUrlAppliable(activationString);
	final String securedPassword = getHashGeneratorService().generateValue(
		account.getPassword());
	account.setActivationString(activationString);
	account.setPassword(securedPassword);
	account.setRole(UserRoles.USER.getRole());
	if (account.getStatus() == null) {
	    account.setStatus(ActivationStatus.PENDING);
	}
    }

    public void deactivateAccount(final Account account) {
	Validate.notNull(account, "account cannot be null while unactivation");
	accountService.deactivateAccount(account);
    }

    public AccountService getAccountService() {
	return accountService;
    }

    public HashGeneratorService getHashGeneratorService() {
	return hashGeneratorService;
    }

    public MapperFactory getMapperFactory() {
	return mapperFactory;
    }

    private String makeUrlAppliable(final String activationString) {
	return activationString.replace(".", "_").replace("\\", "_")
		.replace("/", "_");
    }

    public void setAccountService(final AccountService accountService) {
	this.accountService = accountService;
    }

    public void setHashGeneratorService(
	    final HashGeneratorService hashGeneratorService) {
	this.hashGeneratorService = hashGeneratorService;
    }

    public void setMapperFactory(final MapperFactory mapperFactory) {
	this.mapperFactory = mapperFactory;
    }

    @Override
    public void updateAccount(final Account account) {
	Validate.notNull(account, "account cannot be null while update");
	Validate.notNull(account.getEmail(), "email field is mandatory");
	if (accountService.findByEmail(account.getEmail()) != null) {
	    accountService.updateUserAccount(account);
	}
    }

}
