package com.piramida.dao;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

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

import com.piramida.dao.pendingqueue.PendingQueueDao;
import com.piramida.entity.Account;
import com.piramida.entity.PendingQueue;
import com.piramida.entity.Queue;
import com.piramida.entity.QueueType;

@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/WEB-INF/appContext.xml" })
public class BasicPendingQueueOperationTest implements ApplicationContextAware {

    private static final int COMMON_ID = 1;

    private static final String QUEUE_TYPE = "queueType";

    @Autowired
    private PendingQueueDao pendingQueueDao;

    private PendingQueue testPendingQueue;

    @Test
    public void shouldClearAll() {
	pendingQueueDao.deleteAll();
	final List<PendingQueue> actual = pendingQueueDao.findAll();
	assertTrue("not all were deleted", actual.isEmpty());
    }

    @Test
    public void shouldCreatePendingPosition() {
	pendingQueueDao.deleteAll();
	initPendingQueue();
	pendingQueueDao.save(testPendingQueue);
	assertNotNull("id wasn't set", testPendingQueue.getId());
    }

    @Test
    public void shouldDeletePendingPosition() {

    }

    public void setApplicationContext(
	    final ApplicationContext applicationContext) throws BeansException {

    }

    private void initPendingQueue() {
	testPendingQueue = new PendingQueue();
	testPendingQueue.setAccount(getAccount());
	testPendingQueue.setQueue(getQueue());

    }

    private Queue getQueue() {
	final Queue queue = new Queue();
	queue.setId(COMMON_ID);
	queue.setAccount(getAccount());
	queue.setPosition(2);
	queue.setQueueType(QueueType.C500);
	return queue;
    }

    private Account getAccount() {
	final Account account = new Account();
	account.setId(COMMON_ID);
	return account;
    }

}
