package com.piramida.facade;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.piramida.entity.Account;
import com.piramida.entity.EmailType;
import com.piramida.facade.impl.DefaultAccountFacade;
import com.piramida.service.account.AccountService;
import com.piramida.service.mail.MailService;
import com.piramida.service.security.HashGeneratorService;

public class AccountFacadeTest {

    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String ACTIVATE_USER_STRING = "activateUserString";
    private DefaultAccountFacade testInstance;
    @Mock
    private AccountService accountServiceMock;
    @Mock
    private HashGeneratorService hashGeneratorServiceMock;
    @Mock
    private MailService mailServiceMock;
    private Account account;

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	testInstance = new DefaultAccountFacade();
	testInstance.setAccountService(accountServiceMock);
	testInstance.setHashGeneratorService(hashGeneratorServiceMock);
	testInstance.setMailService(mailServiceMock);

	initAccount();

	when(hashGeneratorServiceMock.generateValue(EMAIL)).thenReturn(
		ACTIVATE_USER_STRING);
    }

    @Test
    public void shouldActivateAccount() {
	testInstance.activateAccount(ACTIVATE_USER_STRING);
	verify(accountServiceMock).activateUserAccount(ACTIVATE_USER_STRING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotActivateAccountNullActivateionString() {
	testInstance.activateAccount(null);
    }

    @Ignore
    @Test
    public void shouldCreateAccount() {
	// testInstance.createAccount(account);
	verify(hashGeneratorServiceMock).generateValue(EMAIL);
	verify(accountServiceMock).createUserAccount(account);
	verify(mailServiceMock).sendEmail(EmailType.REGISTRATION, account);
	Assert.assertNotNull("Activation string wasn't set",
		account.getActivationString());

    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateAccountFailedValidationOfFields() {
	account.setEmail(null);
	account.setPassword(null);
	// testInstance.createAccount(account);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateAccountNullPAssed() {
	testInstance.createAccount(null);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateAccountInvalidEmail() {
	when(accountServiceMock.findByEmail(EMAIL)).thenReturn(account);
	// testInstance.createAccount(account);
    }

    @Test
    public void shouldDeactivateAccount() {
	testInstance.deactivateAccount(account);
	verify(accountServiceMock).deactivateAccount(account);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotDeactivateAccountNullPAssed() {
	testInstance.deactivateAccount(null);
    }

    private void initAccount() {
	account = new Account();
	account.setActivationString(ACTIVATE_USER_STRING);
	account.setEmail(EMAIL);
	account.setPassword(PASSWORD);
    }
}
