package com.piramida.service.queue;

import com.piramida.entity.Queue;

public interface QueueService {

    void putInQueue(Queue queue);

    void remove(Queue queue);

    void switchPositions(final Queue queue, final Queue secondRow);

    Queue getFirst(String queueType);

    void increaseFirstRowPaymentCount(String queueType);

    Queue findById(final int i);
}
