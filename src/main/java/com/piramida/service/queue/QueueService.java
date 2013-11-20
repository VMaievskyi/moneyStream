package com.piramida.service.queue;

import com.piramida.controller.exception.BusinessException;
import com.piramida.entity.Account;
import com.piramida.entity.PendingQueue;
import com.piramida.entity.Queue;
import com.piramida.entity.QueueType;

public interface QueueService {

    void putInQueue(Queue queue);

    void remove(Queue queue);

    void switchPositions(final Queue queue, final Queue secondRow);

    Queue getFirst(String queueType);

    void placeNewQueueRecord(QueueType queueType, Account account)
	    throws BusinessException;

    Queue findById(final int i);

    Queue prepareQueueRecordForInsert(final QueueType queueTypeVal);

    Queue createQueueForInsert(final QueueType queueType, final Account account)
	    throws BusinessException;

    PendingQueue placeRecordWithPaying(final QueueType queueType,
	    final Account account, final Queue first) throws BusinessException;

}
