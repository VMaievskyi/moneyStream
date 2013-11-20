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
	final QueueType queueTypeVal = getQueueTypeByName(queueType);

	final Account account = getCurrentAccount();

	final Queue first = queueService.getFirst(queueType);
	if ((first != null) && checkIfQueueIspayedOff(first)) {
	    final PendingQueue pendingQueue = queueService
		    .placeRecordWithPaying(queueTypeVal, account, first);
	    // TODO: GENERATE LINK
	} else {
	    // TODO: Handle when first queue is filled with payment
	    queueService.placeNewQueueRecord(queueTypeVal, account);
	}
    }

    private boolean checkIfQueueIspayedOff(final Queue first) {
	return first.getPendingQueues().size() < first
		.getRequiredPaymentCount();
    }

    @Override
    public void putInQueue(final String queueType, final Integer accountId) {
	final Account accountById = accountService.findById(accountId);
	if (accountById != null) {
	    final Queue queue = queueService
		    .prepareQueueRecordForInsert(getQueueTypeByName(queueType));
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

    private Account getCurrentAccount() {
	final Account account = (Account) SecurityContextHolder.getContext()
		.getAuthentication().getPrincipal();
	return account;
    }

    private QueueType getQueueTypeByName(final String queueType) {
	final QueueType queueTypeVal = getQueueTypeHolder().getQueuTypeByName(
		queueType);
	return queueTypeVal;
    }

}
