package com.piramida.facade.queue;

import com.piramida.controller.exception.BusinessException;

public interface QueueFacade {

    void putInQueue(final String queueType) throws BusinessException;

    void putInQueue(String queueType, final Integer accountId);

    void removeFromQueue(Integer id);

    void swapQueues(Integer id1, Integer id2);
}
