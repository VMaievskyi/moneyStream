package com.piramida.entity;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
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

@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/WEB-INF/appContext.xml" })
public class BasicQueueOperationsTest implements ApplicationContextAware {

    @Autowired
    private QueueDao queueDao;

    private Queue queue;

    @Test
    public void shouldDeleteAll() {
	queueDao.deleteAll();
	final List<Queue> allQueueEntry = queueDao.findAll();
	Assert.assertTrue("some queue entry were returned",
		allQueueEntry.isEmpty());
    }

    @Test
    public void shouldAddAccountIntoQueue() {
	queueDao.deleteAll();
	initQueue();
	queueDao.save(queue);

	final List<Queue> allQueueEntry = queueDao.findAll();
	Assert.assertFalse("no queue entry were returned",
		allQueueEntry.isEmpty());
	Assert.assertTrue("entry were not saved", allQueueEntry.contains(queue));

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
	Assert.assertFalse(allQueueEntry.contains(queue));
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldThrowConstraintViolationIfIndexBroken() {
	queueDao.deleteAll();
	initQueue();
	queueDao.save(queue);
	initQueue();
	queueDao.save(queue);
    }

    public void setApplicationContext(
	    final ApplicationContext applicationContext) throws BeansException {

    }

    private void initQueue() {
	queue = new Queue();
	queue.setQueueType(QueueType.C500.toString());
	queue.setPosition(1);
    }
}
