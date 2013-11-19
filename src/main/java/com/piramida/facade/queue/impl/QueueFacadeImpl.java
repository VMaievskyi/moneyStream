package com.piramida.facade.queue.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.piramida.controller.exception.BusinessException;
import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.PendingQueue;
import com.piramida.entity.Queue;
import com.piramida.entity.QueueType;
import com.piramida.entity.QueueTypeHolder;
import com.piramida.facade.queue.QueueFacade;
import com.piramida.service.account.AccountService;
import com.piramida.service.queue.QueueService;

@Service("queueFacade")
public class QueueFacadeImpl implements QueueFacade {

    @Autowired
    private QueueService queueService;
    @Autowired
    @Qualifier("queueTypeHolder")
    private QueueTypeHolder queueTypeHolder;
    @Autowired
    private AccountService accountService;

    public QueueService getQueueService() {
	return queueService;
    }

    public QueueTypeHolder getQueueTypeHolder() {
	return queueTypeHolder;
    }

    @Override
    public void putInQueue(final String queueType) throws BusinessException {
	final Queue queue = prepareQueueRecordForInsert(queueType);
	queue.setStatus(ActivationStatus.PENDING);
	final Account account = (Account) SecurityContextHolder.getContext()
		.getAuthentication().getPrincipal();
	queue.setAccount(account);
	final Queue queueForInsert = createQueueForInsert(queueType, account);

	final PendingQueue queueRecord = queueService.placeNewQueueRecord(
		queueType, account, queueForInsert);
	// TODO: GENERATE LINK
    }

    @Override
    public void putInQueue(final String queueType, final Integer accountId) {
	final Account accountById = accountService.findById(accountId);
	if (accountById != null) {
	    final Queue queue = prepareQueueRecordForInsert(queueType);
	    queue.setAccount(accountById);
	    queue.setStatus(ActivationStatus.ACTIVE);
	    getQueueService().putInQueue(queue);
	}
    }

    @Override
    public void removeFromQueue(final Integer id) {
	final Queue queueById = getQueueService().findById(id);
	if (queueById == null) {
	    throw new IllegalArgumentException("No queue with id found");
	}
	getQueueService().remove(queueById);
    }

    public void setQueueService(final QueueService queueService) {
	this.queueService = queueService;
    }

    public void setQueueTypeHolder(final QueueTypeHolder queueTypeHolder) {
	this.queueTypeHolder = queueTypeHolder;
    }

    @Override
    public void swapQueues(final Integer id1, final Integer id2) {
	final Queue byId1 = queueService.findById(id1);
	final Queue byId2 = queueService.findById(id2);

	if ((byId1 != null) && (byId2 != null)) {
	    getQueueService().switchPositions(byId1, byId2);
	}

    }

    protected Queue createBlankQueue() {
	return new Queue();
    }

    /**
     * Create record before insertion in queue record
     * 
     * @param queueType
     * @return
     */
    private Queue prepareQueueRecordForInsert(final String queueType) {
	final QueueType queueTypeVal = getQueueTypeHolder().getQueuTypeByName(
		queueType);
	if (queueTypeVal == null) {
	    throw new IllegalArgumentException("Unknown queue type");
	}

	final Queue queue = createBlankQueue();
	queue.setQueueType(queueType);
	queue.setRequiredPaymentCount(queueTypeVal.getRequiredPaymentCount());
	return queue;
    }

    private Queue createQueueForInsert(final String queueType,
	    final Account account) throws BusinessException {
	final Queue queue = new Queue();
	final QueueType queueTypeByName = queueTypeHolder
		.getQueuTypeByName(queueType);
	if (queueTypeByName == null) {
	    throw new BusinessException("queue.invalidType");
	}
	queue.setAccount(account);
	queue.setQueueType(queueType);
	queue.setRequiredPaymentCount(queueTypeByName.getRequiredPaymentCount());
	queue.setStatus(ActivationStatus.PENDING);
	return queue;
    }

}
