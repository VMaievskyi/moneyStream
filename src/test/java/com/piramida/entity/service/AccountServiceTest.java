package com.piramida.entity.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.piramida.dao.account.AccountDao;
import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.service.account.impl.DefaultAccountService;

public class AccountServiceTest {

    private static final String ACTIVATION_STRING = "activationString";

    private static final String PASSWORD = "password";

    private static final String EMAIL = "email";

    @Mock
    private AccountDao accountDaoMock;

    private DefaultAccountService testInstance;
    private Account account;

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	testInstance = new DefaultAccountService();
	testInstance.setAccountDao(accountDaoMock);

	initAccountModel();

	when(accountDaoMock.findByActivationString(ACTIVATION_STRING))
		.thenReturn(account);
    }

    @Test
    public void shouldActivateAccount() {
	account.setStatus(ActivationStatus.PENDING);
	testInstance.activateUserAccount(ACTIVATION_STRING);
	verify(accountDaoMock).findByActivationString(ACTIVATION_STRING);
	Assert.assertEquals("status wasn't changed", ActivationStatus.ACTIVE,
		account.getStatus());
    }

    @Test
    public void shouldNotActivateAccount() {
	account.setStatus(ActivationStatus.ACTIVE);
	testInstance.activateUserAccount(ACTIVATION_STRING);
	verify(accountDaoMock).findByActivationString(ACTIVATION_STRING);
	verify(accountDaoMock, Mockito.never()).save(account);
    }

    @Test
    public void shouldCreateAccount() {
	testInstance.createUserAccount(account);
	verify(accountDaoMock).save(account);
    }

    @Test
    public void shouldDeactivateAccount() {
	account.setStatus(ActivationStatus.ACTIVE);
	testInstance.deactivateAccount(account);
	verify(accountDaoMock).save(account);
	Assert.assertEquals("status wasn't changed", ActivationStatus.PENDING,
		account.getStatus());

    }

    @Test
    public void shouldNotDeactivateAccount() {
	account.setStatus(ActivationStatus.PENDING);
	testInstance.deactivateAccount(account);
	verify(accountDaoMock, Mockito.never()).save(account);

    }

    private void initAccountModel() {
	account = new Account();
	account.setEmail(EMAIL);
	account.setPassword(PASSWORD);
	account.setActivationString(ACTIVATION_STRING);
	account.setStatus(ActivationStatus.PENDING);
    }
}
