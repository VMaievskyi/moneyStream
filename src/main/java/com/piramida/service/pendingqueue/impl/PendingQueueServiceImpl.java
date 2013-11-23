package com.piramida.service.pendingqueue.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.piramida.dao.pendingqueue.PendingQueueDao;
import com.piramida.dao.queue.QueueDao;
import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.PendingQueue;
import com.piramida.service.pendingqueue.PendingQueueService;

@Component("pendingQueueService")
public class PendingQueueServiceImpl implements PendingQueueService {

    @Autowired
    private PendingQueueDao pendingQueueDao;
    @Autowired
    private QueueDao queueDao;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public List<PendingQueue> getAllRecordsForCurrentAccount(
	    final Account currentAccount) {
	return pendingQueueDao.getAllPending();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void approvePendingQueue(final String receiptId) {
	final PendingQueue approvedQueue = pendingQueueDao
		.getBySecureId(receiptId);
	approvedQueue.setStatus(ActivationStatus.ACTIVE);
	approvedQueue.getGarantedQueue().setStatus(ActivationStatus.ACTIVE);
	approvedQueue.getQueue().setStatus(ActivationStatus.ACTIVE);
	queueDao.save(approvedQueue.getGarantedQueue());
	queueDao.save(approvedQueue.getQueue());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void rejectPendingQueue(final String receiptId) {
	final PendingQueue rejectedQueue = pendingQueueDao
		.getBySecureId(receiptId);
	rejectedQueue.setStatus(ActivationStatus.REJECTED);
	pendingQueueDao.save(rejectedQueue);
    }

}
