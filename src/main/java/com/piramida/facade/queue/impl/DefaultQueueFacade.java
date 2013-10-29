package com.piramida.facade.queue.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.piramida.entity.Queue;
import com.piramida.facade.queue.QueueFacade;
import com.piramida.service.queue.QueueService;

public class DefaultQueueFacade implements QueueFacade {

    @Autowired
    private QueueService queueService;

    public Queue findById(final int id) {
	return getQueueService().findById(id);
    }

    public Queue findFirst(final String queueType) {
	return getQueueService().getFirst(queueType);
    }

    public QueueService getQueueService() {
	return queueService;
    }

    public void setQueueService(final QueueService queueService) {
	this.queueService = queueService;
    }

}
