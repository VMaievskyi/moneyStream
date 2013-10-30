package com.piramida.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

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

@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional()
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
	queue.setRequiredPaymentCount(2);
	queueDao.save(queue);
	queueDao.delete(queue);

	final List<Queue> allQueueEntry = queueDao.findAll();
	assertFalse(allQueueEntry.contains(queue));
    }

    @Test
    public void shouldUpdatQueue() {
	queueDao.deleteAll();
	initQueue();
	queueDao.save(queue);
	queue.setRequiredPaymentCount(10);
	queueDao.save(queue);
	final List<Queue> actual = queueDao.findAll();
	assertEquals("More then 1 item were in db", 1, actual.size());
	assertEquals("Item wasn't updated", 10, actual.get(0)
		.getRequiredPaymentCount().intValue());
    }

    @Test
    public void shouldFindFirstRowInActiveStatus() {
	queueDao.deleteAll();
	initQueue();
	queue.setStatus(ActivationStatus.PENDING);
	queue.setRequiredPaymentCount(1);
	queueDao.save(queue);

	initQueue();
	queue.setStatus(ActivationStatus.ACTIVE);
	queue.setRequiredPaymentCount(2);
	queueDao.save(queue);

	initQueue();
	queue.setStatus(ActivationStatus.ACTIVE);
	queue.setRequiredPaymentCount(3);
	queueDao.save(queue);

	final Queue first = queueDao.getFirst(queue.getQueueType());
	assertEquals("not first row was returned", 2, first
		.getRequiredPaymentCount().intValue());

    }

    @Test
    public void shouldSwapPositions() {
	queueDao.deleteAll();
	initQueue();
	queueDao.save(queue);
	final Queue q = new Queue();

	q.setAccount(createTestAccount2());
	q.setPaymentCount(5);
	q.setQueueType("fff");
	q.setStatus(ActivationStatus.ACTIVE);
	queueDao.save(q);
	queueDao.switchPositions(queue, q);
	assertEquals("Value of first queue wasn't changed",
		ActivationStatus.PENDING, queue.getAccount().getStatus());
	assertEquals("Value of first queues payment count wasn't changed", 5,
		queue.getPaymentCount().intValue());

    }

    @Test
    public void shouldFindById() {
	queueDao.deleteAll();
	initQueue();
	queueDao.save(queue);
	final Queue record = queueDao.findById(queue.getId());
	assertEquals("Records not same", queue, record);
    }

    @Test
    public void shouldSelectRowsInRange() {
	queueDao.deleteAll();
	for (int i = 0; i < 10; i++) {
	    initQueue();
	    queue.setRequiredPaymentCount(i);
	    queueDao.save(queue);
	}
	final List<Queue> result = queueDao.findAllRange(2, 4);
	assertEquals("Wrong count of elements was returned", 6, result.size());

	assertEquals("wrong record from table was returned", 2, result.get(0)
		.getRequiredPaymentCount().intValue());
    }

    public void setApplicationContext(
	    final ApplicationContext applicationContext) throws BeansException {

    }

    private void initQueue() {
	queue = new Queue();
	queue.setQueueType("C500");
	queue.setRequiredPaymentCount(1);
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

    private Account createTestAccount2() {
	final Account account = new Account();
	account.setEmail(ACCOUNT_EMAIL);
	account.setId(1);
	account.setPassword(ACCOUNT_PASSWORD);
	account.setStatus(ActivationStatus.PENDING);
	return account;
    }
}
