package com.piramida.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.piramida.dao.account.AccountDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/WEB-INF/appContext.xml" })
public class BasicAccountOperationTest implements ApplicationContextAware {

    private static final String STATUS = "status";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    @Autowired
    private AccountDao accountDao;

    Account testAccount;

    @Before
    public void setUp() {
	testAccount = new Account();
	testAccount.setEmail(EMAIL);
	testAccount.setPassword(PASSWORD);
	testAccount.setStatus(STATUS);
    }

    @Test
    public void shouldWipeAllAccounts() {
	accountDao.deleteAll();
	assertTrue("not all accounts were deleted", accountDao.findAll()
		.isEmpty());
    }

    @Test
    public void shouldCreateNewAccount() {

	accountDao.create(testAccount);

	assertNotNull("Nothing was created", testAccount.getId());

    }

    @Test
    public void shouldFindAtLeastOneAccount() {
	final List<Account> accounts = accountDao.findAll();
	assertNotNull("No accaunts was found", accounts);
	assertFalse("No result returned", accounts.isEmpty());
    }

    public void setApplicationContext(
	    final ApplicationContext applicationContext) throws BeansException {

    }

}
