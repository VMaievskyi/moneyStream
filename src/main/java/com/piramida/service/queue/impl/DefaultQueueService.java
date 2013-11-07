package com.piramida.service.queue.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.piramida.controller.exception.BusinessException;
import com.piramida.dao.queue.QueueDao;
import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.PendingQueue;
import com.piramida.entity.Queue;
import com.piramida.service.queue.QueueService;

public class DefaultQueueService implements QueueService {

    private static Logger LOG = LoggerFactory
	    .getLogger(DefaultQueueService.class);

    @Autowired
    private QueueDao queueDao;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void putInQueue(final Queue queue) {
	queueDao.save(queue);
	if (LOG.isDebugEnabled()) {
	    LOG.debug(" queue record  was saved{}", queue);
	}
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void remove(final Queue queue) {
	if (LOG.isDebugEnabled()) {
	    LOG.debug("about to delete queue record {}", queue);
	}
	queueDao.delete(queue);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void switchPositions(final Queue queue, final Queue secondRow) {
	if (LOG.isDebugEnabled()) {
	    LOG.debug("about to switch postions for queue records {} and {}",
		    queue, secondRow);
	}
	queueDao.switchPositions(queue, secondRow);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Queue getFirst(final String queueType) {
	return queueDao.getFirst(queueType);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public PendingQueue increaseFirstRowPaymentCount(final String queueType,
	    final Account account) throws BusinessException {
	final Queue first = queueDao.getFirst(queueType);
	if (first.getPendingQueues().size() < first.getRequiredPaymentCount()) {
	    final PendingQueue pendingQueueRecord = createPendingQueueRecord(
		    account, first);
	    first.getPendingQueues().add(pendingQueueRecord);
	    return pendingQueueRecord;
	}
	throw new BusinessException(
		"get first method returns record filled with candidates");

    }

    @Transactional
    public Queue findById(final int id) {
	return getQueueDao().findById(id);
    }

    @Transactional
    public List<Queue> findAllRange(final int istartIndex,
	    final int countToReturn) {
	return getQueueDao().findAllRange(istartIndex, countToReturn);

    }

    public QueueDao getQueueDao() {
	return queueDao;
    }

    public void setQueueDao(final QueueDao queueDao) {
	this.queueDao = queueDao;
    }

    private PendingQueue createPendingQueueRecord(final Account account,
	    final Queue queue) {
	final PendingQueue pendingQueue = new PendingQueue();
	pendingQueue.setPendingQueueOwner(account);
	pendingQueue.setStatus(ActivationStatus.PENDING);
	pendingQueue.setQueue(queue);
	return pendingQueue;
    }

}
