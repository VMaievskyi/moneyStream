package com.piramida.facade.queue;

import java.util.List;

import com.piramida.controller.exception.BusinessException;
import com.piramida.entity.QueueType;

public interface QueueFacade {

    void putInQueue(final String queueType) throws BusinessException;

    void putInQueue(String queueType, final Integer accountId);

    void removeFromQueue(Integer id);

    void swapQueues(Integer id1, Integer id2);

    List<QueueType> getAllQueueTypes();

}
