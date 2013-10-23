package com.piramida.service.queue.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.piramida.dao.queue.QueueDao;
import com.piramida.entity.Queue;
import com.piramida.service.queue.QueueService;

public class DefaultQueueService implements QueueService {

    @Autowired
    private QueueDao queueDao;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void putInQueue(final Queue queue) {
	queueDao.save(queue);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void remove(final Queue queue) {
	queueDao.delete(queue);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void switchPositions(final Queue queue, final Queue secondRow) {
	queueDao.switchPositions(queue, secondRow);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Queue getFirst() {
	return queueDao.getFirst();
    }

    public QueueDao getQueueDao() {
	return queueDao;
    }

    public void setQueueDao(final QueueDao queueDao) {
	this.queueDao = queueDao;
    }

}
