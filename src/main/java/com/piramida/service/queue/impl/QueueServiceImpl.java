package com.piramida.service.queue.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.piramida.controller.exception.BusinessException;
import com.piramida.dao.queue.QueueDao;
import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.PendingQueue;
import com.piramida.entity.Queue;
import com.piramida.service.queue.QueueService;

@Service
public class QueueServiceImpl implements QueueService {

    private static Logger LOG = LoggerFactory.getLogger(QueueServiceImpl.class);

    @Autowired
    private QueueDao queueDao;

    @Transactional
    public List<Queue> findAllRange(final int istartIndex,
	    final int countToReturn) {
	return getQueueDao().findAllRange(istartIndex, countToReturn);

    }

    @Override
    @Transactional
    public Queue findById(final int id) {
	return getQueueDao().findById(id);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Queue getFirst(final String queueType) {
	return queueDao.getFirst(queueType);
    }

    public QueueDao getQueueDao() {
	return queueDao;
    }

    // TODO: handle when no first result available
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public PendingQueue increaseFirstRowPaymentCount(final String queueType,
	    final Account account) throws BusinessException {
	final Queue first = queueDao.getFirst(queueType);
	if (first.getPendingQueues().size() < first.getRequiredPaymentCount()) {
	    final PendingQueue pendingQueueRecord = createPendingQueueRecord(
		    account, first);
	    first.getPendingQueues().add(pendingQueueRecord);
	    first.setStatus(ActivationStatus.PENDING);
	    putInQueue(first);
	    return pendingQueueRecord;
	}
	throw new BusinessException(
		"get first method returns record filled with candidates");

    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void putInQueue(final Queue queue) {
	queueDao.save(queue);
	if (LOG.isDebugEnabled()) {
	    LOG.debug(" queue record  was saved{}", queue);
	}
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void remove(final Queue queue) {
	if (LOG.isDebugEnabled()) {
	    LOG.debug("about to delete queue record {}", queue);
	}
	queueDao.delete(queue);
    }

    public void setQueueDao(final QueueDao queueDao) {
	this.queueDao = queueDao;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void switchPositions(final Queue queue, final Queue secondRow) {
	if (LOG.isDebugEnabled()) {
	    LOG.debug("about to switch postions for queue records {} and {}",
		    queue, secondRow);
	}
	queueDao.switchPositions(queue, secondRow);
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
