package com.piramida.facade;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.piramida.entity.Account;
import com.piramida.entity.dto.AccountDto;
import com.piramida.entity.mapper.factory.MapperFactory;
import com.piramida.entity.mapper.impl.AbstractDtoEntityMapper;
import com.piramida.facade.account.impl.AccountFacadeImpl;
import com.piramida.service.account.AccountService;
import com.piramida.service.mail.persister.IPersister;
import com.piramida.service.security.HashGeneratorService;

public class AccountFacadeTest {

    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String ACTIVATE_USER_STRING = "activateUserString";
    @Mock
    private AccountService accountServiceMock;
    @Mock
    private HashGeneratorService hashGeneratorServiceMock;
    @Mock
    private IPersister persister;
    @Mock
    private MapperFactory mapperFactoryMock;
    @Mock
    private AbstractDtoEntityMapper mapperMock;
    private AccountDto accountDto;
    private Account account;
    @InjectMocks
    private final AccountFacadeImpl testInstance = new AccountFacadeImpl();

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	initAccount();

	when(hashGeneratorServiceMock.generateValue(EMAIL)).thenReturn(
		ACTIVATE_USER_STRING);
	when(mapperFactoryMock.createInstance(AccountDto.class)).thenReturn(
		mapperMock);
	when(mapperMock.map(Mockito.any(AccountDto.class))).thenReturn(account);
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

    @Test
    public void shouldCreateAccount() {
	initAccountDto();
	testInstance.createAccount(accountDto);
	verify(hashGeneratorServiceMock).generateValue(EMAIL);
	verify(accountServiceMock).createUserAccount(account);

	verify(persister).persistMail(account);

	Assert.assertNotNull("Activation string wasn't set",
		account.getActivationString());

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateAccountFailedValidationOfFields() {
	account.setEmail(null);
	account.setPassword(null);
	testInstance.createAccount(accountDto);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateAccountNullPAssed() {
	testInstance.createAccount(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateAccountInvalidEmail() {
	when(accountServiceMock.findByEmail(EMAIL)).thenReturn(account);
	testInstance.createAccount(accountDto);
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

    private void initAccountDto() {
	accountDto = new AccountDto();
	accountDto.setActivationString(ACTIVATE_USER_STRING);
	accountDto.setEmail(EMAIL);
	accountDto.setPassword(PASSWORD);
    }

    private void initAccount() {
	account = new Account();
	account.setActivationString(ACTIVATE_USER_STRING);
	account.setEmail(EMAIL);
	account.setPassword(PASSWORD);
    }
}
