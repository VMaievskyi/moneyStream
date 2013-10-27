package com.piramida.service.queue;

import com.piramida.entity.Queue;
import com.piramida.entity.QueueType;

public interface QueueService {

    void putInQueue(Queue queue);

    void remove(Queue queue);

    void switchPositions(final Queue queue, final Queue secondRow);

    Queue getFirst(QueueType queueType);

    void increaseFirstRowPaymentCount(QueueType queueType);
}
