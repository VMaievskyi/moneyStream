package com.piramida.facade.queue.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.Queue;
import com.piramida.entity.QueueType;
import com.piramida.entity.QueueTypeHolder;
import com.piramida.facade.queue.QueueFacade;
import com.piramida.service.queue.QueueService;

public class DefaultQueueFacade implements QueueFacade {

    @Autowired
    private QueueService queueService;
    @Autowired
    @Qualifier("queueTypeHolder")
    private QueueTypeHolder queueTypeHolder;

    public void putInQueue(final String queueType) {
	final QueueType queueTypeVal = getQueueTypeHolder().getQueuTypeByName(
		queueType);
	if (queueTypeVal == null) {
	    throw new IllegalArgumentException("Unknown queue type");
	}

	final Queue queue = createBlankQueue();
	queue.setPaymentCount(0);
	queue.setQueueType(queueType);
	queue.setRequiredPaymentCount(queueTypeVal.getRequiredPaymentCount());
	queue.setStatus(ActivationStatus.PENDING);
	queue.setAccount(getPrincipal());
	getQueueService().putInQueue(queue);

    }

    private Account getPrincipal() {
	return (Account) SecurityContextHolder.getContext().getAuthentication()
		.getPrincipal();
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
