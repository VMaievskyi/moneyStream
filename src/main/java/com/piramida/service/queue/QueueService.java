package com.piramida.service.queue;

import com.piramida.controller.exception.BusinessException;
import com.piramida.entity.Account;
import com.piramida.entity.PendingQueue;
import com.piramida.entity.Queue;

public interface QueueService {

    void putInQueue(Queue queue);

    void remove(Queue queue);

    void switchPositions(final Queue queue, final Queue secondRow);

    Queue getFirst(String queueType);

    PendingQueue placeNewQueueRecord(String queueType, Account account,
	    Queue queueForInsert) throws BusinessException;

    Queue findById(final int i);

}
