package com.piramida.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
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

import com.piramida.dao.queue.QueueDao;
import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.Queue;
import com.piramida.entity.QueueType;

@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/WEB-INF/appContext.xml" })
public class BasicQueueOperationsTest implements ApplicationContextAware {

    private static final String ACCOUNT_STATUS = "accountStatus";

    private static final String ACCOUNT_PASSWORD = "accountPassword";

    private static final String ACCOUNT_EMAIL = "accountEmail";

    @Autowired
    private QueueDao queueDao;

    private Queue queue;

    @Test
    public void shouldDeleteAll() {
	queueDao.deleteAll();
	final List<Queue> allQueueEntry = queueDao.findAll();
	assertTrue("some queue entry were returned", allQueueEntry.isEmpty());
    }

    @Test
    public void shouldAddAccountIntoQueue() {
	queueDao.deleteAll();
	initQueue();
	queueDao.save(queue);

	final List<Queue> allQueueEntry = queueDao.findAll();
	assertFalse("no queue entry were returned", allQueueEntry.isEmpty());
	assertTrue("entry were not saved", allQueueEntry.contains(queue));

    }

    @Test
    public void shouldDelete() {
	queueDao.deleteAll();
	initQueue();
	queueDao.save(queue);
	initQueue();
	queue.setPosition(2);
	queueDao.save(queue);
	queueDao.delete(queue);

	final List<Queue> allQueueEntry = queueDao.findAll();
	assertFalse(allQueueEntry.contains(queue));
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldThrowConstraintViolationIfIndexBroken() {
	queueDao.deleteAll();
	initQueue();
	queueDao.save(queue);
	initQueue();
	queueDao.save(queue);
    }

    @Test
    public void shouldUpdatQueue() {
	queueDao.deleteAll();
	initQueue();
	queueDao.save(queue);
	queue.setPosition(10);
	queueDao.save(queue);
	final List<Queue> actual = queueDao.findAll();
	assertEquals("More then 1 item were in db", 1, actual.size());
	assertEquals("Item wasn't updated", 10, actual.get(0).getPosition()
		.intValue());
    }

    public void setApplicationContext(
	    final ApplicationContext applicationContext) throws BeansException {

    }

    private void initQueue() {
	queue = new Queue();
	queue.setQueueType(QueueType.C500.toString());
	queue.setPosition(1);
	queue.setAccount(createTestAccount());
    }

    private Account createTestAccount() {
	final Account account = new Account();
	account.setEmail(ACCOUNT_EMAIL);
	account.setId(1);
	account.setPassword(ACCOUNT_PASSWORD);
	account.setStatus(ActivationStatus.ACTIVE);
	return account;
    }
}
