package com.piramida.facade.queue.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.piramida.controller.exception.BusinessException;
import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.PendingQueue;
import com.piramida.entity.Queue;
import com.piramida.entity.QueueType;
import com.piramida.entity.QueueTypeHolder;
import com.piramida.entity.dto.QueueDto;
import com.piramida.entity.dto.QueueInfoDto;
import com.piramida.entity.mapper.factory.MapperFactory;
import com.piramida.entity.mapper.impl.AbstractDtoEntityMapper;
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
    @Autowired
    private MapperFactory mapperFactory;

    public QueueService getQueueService() {
	return queueService;
    }

    public QueueTypeHolder getQueueTypeHolder() {
	return queueTypeHolder;
    }

    @Override
    public Queue putInQueue(final String queueType) throws BusinessException {
	final QueueType queueTypeVal = getQueueTypeByName(queueType);

	final Account account = accountService.getCurrentAccount();

	return placeQueueRecord(queueTypeVal, account);
    }

    private Queue placeQueueRecord(final QueueType queueTypeVal,
	    final Account account) throws BusinessException {
	Queue first = queueService.getFirst(queueTypeVal);
	if ((first != null)) {
	    if (checkIfQueueIspayedOff(first)) {
		final PendingQueue pendingQueue = queueService
			.placeRecordWithPaying(queueTypeVal, account, first);
	    } else {
		first.setStatus(ActivationStatus.DELETE);
		queueService.putInQueue(first);
		first = placeQueueRecord(queueTypeVal, account);
	    }
	} else {
	    queueService.placeNewQueueRecord(queueTypeVal, account);
	    first = null;
	}
	return first;

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

    private QueueType getQueueTypeByName(final String queueType) {
	final QueueType queueTypeVal = getQueueTypeHolder().getQueuTypeByName(
		queueType);
	return queueTypeVal;
    }

    @Override
    public List<QueueType> getAllQueueTypes() {
	final List<QueueType> queueTypes = Lists.newArrayList();
	for (final String name : getQueueTypeHolder().getAllQueueTypeNames()) {
	    queueTypes.add(getQueueTypeByName(name));
	}

	return queueTypes;
    }

    @Override
    public QueueInfoDto getInfoForQueueType(final String currentQueue) {
	final QueueType queuTypeByName = queueTypeHolder
		.getQueuTypeByName(currentQueue);
	final QueueInfoDto infoDto = new QueueInfoDto();

	infoDto.setQueue(queuTypeByName);
	final int numberOfVisiblePositions = queuTypeByName
		.getNumberOfVisiblePositions();
	if (numberOfVisiblePositions != 0) {
	    final AbstractDtoEntityMapper mapper = mapperFactory
		    .createInstance(QueueDto.class);
	    final List<Queue> visibleRows = queueService.findAllRange(
		    queuTypeByName, 0, numberOfVisiblePositions);
	    if (visibleRows != null) {
		final List<QueueDto> dtos = Lists.newLinkedList();
		for (final Queue queue : visibleRows) {
		    dtos.add((QueueDto) mapper.unmap(queue));
		}
		infoDto.setQueueDtos(dtos);
	    }
	}
	return infoDto;
    }

}
