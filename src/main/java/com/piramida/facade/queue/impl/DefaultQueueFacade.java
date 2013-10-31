package com.piramida.facade.queue.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.Queue;
import com.piramida.entity.QueueType;
import com.piramida.entity.QueueTypeHolder;
import com.piramida.facade.queue.QueueFacade;
import com.piramida.service.account.AccountService;
import com.piramida.service.queue.QueueService;

public class DefaultQueueFacade implements QueueFacade {

    @Autowired
    private QueueService queueService;
    @Autowired
    @Qualifier("queueTypeHolder")
    private QueueTypeHolder queueTypeHolder;
    @Autowired
    @Qualifier("accountService")
    private AccountService accountService;

    public void putInQueue(final String queueType, final Account account) {
	final Queue queue = prepareQueueForInsert(queueType);
	queue.setStatus(ActivationStatus.PENDING);
	queue.setAccount(account);
	getQueueService().putInQueue(queue);
    }

    public void putInQueue(final String queueType, final Integer accountId) {
	final Account accountById = accountService.findById(accountId);
	if (accountById != null) {
	    final Queue queue = prepareQueueForInsert(queueType);
	    queue.setAccount(accountById);
	    queue.setStatus(ActivationStatus.ACTIVE);
	    getQueueService().putInQueue(queue);
	}
    }

    private Queue prepareQueueForInsert(final String queueType) {
	final QueueType queueTypeVal = getQueueTypeHolder().getQueuTypeByName(
		queueType);
	if (queueTypeVal == null) {
	    throw new IllegalArgumentException("Unknown queue type");
	}

	final Queue queue = createBlankQueue();
	queue.setPaymentCount(0);
	queue.setQueueType(queueType);
	queue.setRequiredPaymentCount(queueTypeVal.getRequiredPaymentCount());
	return queue;
    }

    public void removeFromQueue(final Integer id) {
	final Queue queueById = getQueueService().findById(id);
	if (queueById == null) {
	    throw new IllegalArgumentException("No queue with id found");
	}
	getQueueService().remove(queueById);
    }

    public void swapQueues(final Integer id1, final Integer id2) {
	final Queue byId1 = queueService.findById(id1);
	final Queue byId2 = queueService.findById(id2);

	if (byId1 != null && byId2 != null) {
	    getQueueService().switchPositions(byId1, byId2);
	}

    }

    protected Queue createBlankQueue() {
	return new Queue();
    }

    public QueueTypeHolder getQueueTypeHolder() {
	return queueTypeHolder;
    }

    public void setQueueTypeHolder(final QueueTypeHolder queueTypeHolder) {
	this.queueTypeHolder = queueTypeHolder;
    }

    public QueueService getQueueService() {
	return queueService;
    }

    public void setQueueService(final QueueService queueService) {
	this.queueService = queueService;
    }
}
