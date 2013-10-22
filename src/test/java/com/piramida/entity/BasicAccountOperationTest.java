package com.piramida.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.piramida.dao.account.AccountDao;

@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/WEB-INF/appContext.xml" })
public class BasicAccountOperationTest implements ApplicationContextAware {

    private static final String EMAIL2 = "email2";
    private static final String WALLET_NUMBER = "1234567";
    private static final String WALLET_TYPE = "wallet_type";
    private static final String STATUS = "status";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    @Autowired
    private AccountDao accountDao;

    private Account testAccount;

    @Test
    public void shouldWipeAllAccounts() {
	accountDao.deleteAll();
	assertTrue("not all accounts were deleted", accountDao.findAll()
		.isEmpty());
    }

    @Test
    public void shouldCreateNewAccount() {
	accountDao.deleteAll();
	testAccount = initTestAccount();
	accountDao.save(testAccount);
	assertNotNull("Nothing was created", testAccount.getId());

    }

    @Test
    public void shouldFindAtLeastOneAccount() {
	accountDao.deleteAll();
	testAccount = initTestAccount();
	accountDao.save(testAccount);
	final List<Account> accounts = accountDao.findAll();
	assertNotNull("No accaunts was found", accounts);
	assertFalse("No result returned", accounts.isEmpty());
    }

    @Test
    public void shouldSaveAccountWithWallet() {
	accountDao.deleteAll();
	testAccount = initTestAccount();
	addWalletToAccount();
	accountDao.save(testAccount);

	final List<Account> result = accountDao.findAll();
	assertFalse("Nothing was created", result.isEmpty());
	assertFalse("account doesn't have wallets", result.get(0).getWallets()
		.isEmpty());
    }

    @Test
    public void shouldFindAccountByEmail() {
	accountDao.deleteAll();
	testAccount = initTestAccount();
	accountDao.save(testAccount);
	testAccount = initTestAccount();
	testAccount.setEmail(EMAIL2);

	final Account actual = accountDao.findByEmail(EMAIL);
	assertEquals("wrong email", EMAIL, actual.getEmail());

    }

    @Test
    public void shouldDeleteConcreteAccount() {
	accountDao.deleteAll();
	testAccount = initTestAccount();
	accountDao.save(testAccount);
	testAccount = initTestAccount();
	testAccount.setEmail(EMAIL2);
	accountDao.save(testAccount);

	accountDao.delete(testAccount);
	final List<Account> actual = accountDao.findAll();
	assertFalse("All was deleted", actual.isEmpty());
	assertEquals("Wrong data was deleted", EMAIL, actual.get(0).getEmail());

    }

    @Test
    public void shouldDeleteWallet() {
	accountDao.deleteAll();
	testAccount = initTestAccount();
	addWalletToAccount();
	accountDao.save(testAccount);

	final Account accountWithWallet = accountDao.findByEmail(testAccount
		.getEmail());
	accountWithWallet.getWallets().clear();
	accountDao.save(accountWithWallet);

	final List<Account> actual = accountDao.findAll();
	assertTrue("more then one entity returned", actual.size() == 1);
	assertTrue("wallets are not empty", actual.get(0).getWallets()
		.isEmpty());
    }

    public void setApplicationContext(
	    final ApplicationContext applicationContext) throws BeansException {

    }

    private Account initTestAccount() {
	final Account testAccount = new Account();
	testAccount.setEmail(EMAIL);
	testAccount.setPassword(PASSWORD);
	testAccount.setStatus(STATUS);
	return testAccount;
    }

    private void addWalletToAccount() {
	final Wallet wallet = new Wallet();
	wallet.setWaletNumber(WALLET_NUMBER);
	wallet.setWalletType(WALLET_TYPE);
	wallet.setOwner(testAccount);
	final Set<Wallet> wallets = new HashSet<Wallet>();
	wallets.add(wallet);
	testAccount.setWallets(wallets);
    }
}
