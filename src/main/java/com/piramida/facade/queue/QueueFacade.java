package com.piramida.facade.queue;

import com.piramida.controller.exception.BusinessException;
import com.piramida.entity.Account;

public interface QueueFacade {
    void putInQueue(final String queueType, Account account)
	    throws BusinessException;

    void putInQueue(String queueType, final Integer accountId);

    void removeFromQueue(Integer id);

    void swapQueues(Integer id1, Integer id2);
}
