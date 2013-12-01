package com.piramida.dao;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.junit.Before;
import org.junit.Ignore;
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

import com.piramida.dao.pendingqueue.PendingQueueDao;
import com.piramida.dao.queue.QueueDao;
import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.PendingQueue;
import com.piramida.entity.Queue;

@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional()
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/WEB-INF/appContext.xml" })
public class PendingQueueDaoTest implements ApplicationContextAware {

    @Autowired
    private QueueDao queueDao;
    @Autowired
    private PendingQueueDao testInstance;

    private Queue queue;
    private PendingQueue pendingQueue;
    private PendingQueue pendingQueueOld;

    @Before
    public void setUp() {
	createInitialData();
    }

    @Ignore
    @Test
    public void shouldDeleteOldQueues() {
	createInitialData();
	addOldPendingQueue();
	addPendingQueue();

	queueDao.save(queue);
	final Calendar cal = Calendar.getInstance();
	cal.add(Calendar.HOUR_OF_DAY, -2);
	final List<PendingQueue> actual = testInstance
		.findInnactiveOlderThen(cal);
	assertTrue("not 1 pending queue returns", actual.size() == 1);
	System.out.println(actual.get(0) instanceof PendingQueue);
	assertEquals("wrong pendingQueue returns",
		pendingQueue.getCreationDate(), actual.get(0).getCreationDate());
    }

    private void createInitialData() {
	queue = new Queue();
	queue.setQueueType("c500");
	queue.setStatus(ActivationStatus.PENDING);
	queue.setRequiredPaymentCount(10);
	queue.setAccount(createAccount());

    }

    private Account createAccount() {
	final Account account = new Account();
	account.setId(1);
	return account;
    }

    public void addOldPendingQueue() {
	pendingQueueOld = new PendingQueue();
	pendingQueueOld.setPendingQueueOwner(queue.getAccount());
	pendingQueueOld.setQueue(queue);
	pendingQueueOld.setStatus(ActivationStatus.PENDING);
	pendingQueueOld.setCreationDate(DateTime.now().toDate());
	pendingQueueOld.setGarantedQueue(createGarantedQueue());
	queue.getPendingQueues().add(pendingQueueOld);
    }

    private Queue createGarantedQueue() {
	final Queue queue = new Queue();
	queue.setId(1);
	queue.setQueueType("c500");
	queue.setStatus(ActivationStatus.PENDING);
	queue.setRequiredPaymentCount(10);
	queue.setAccount(createAccount());
	return queue;
    }

    public void addPendingQueue() {
	final MutableDateTime mutableDateTime = DateTime.now()
		.toMutableDateTime();
	mutableDateTime.addHours(-3);
	pendingQueue = new PendingQueue();
	pendingQueue.setPendingQueueOwner(queue.getAccount());
	pendingQueue.setQueue(queue);
	pendingQueue.setStatus(ActivationStatus.PENDING);
	pendingQueue.setCreationDate(mutableDateTime.toDate());
	pendingQueue.setGarantedQueue(createGarantedQueue());
	queue.getPendingQueues().add(pendingQueue);
    }

    @Override
    public void setApplicationContext(
	    final ApplicationContext applicationContext) throws BeansException {

    }

}
