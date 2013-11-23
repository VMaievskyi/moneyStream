package com.piramida.facade.pendingqueue.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piramida.entity.Account;
import com.piramida.entity.PendingQueue;
import com.piramida.entity.dto.PendingQueueDtoList;
import com.piramida.entity.mapper.factory.MapperFactory;
import com.piramida.entity.mapper.impl.AbstractDtoEntityMapper;
import com.piramida.facade.pendingqueue.PendingQueueFacade;
import com.piramida.service.account.AccountService;
import com.piramida.service.pendingqueue.PendinQueueService;

@Service("pendingQueueFacade")
public class PendingQueueFacadeImpl implements PendingQueueFacade {

    @Autowired
    private PendinQueueService pendingQueueService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private MapperFactory mapperFactory;

    @Override
    public PendingQueueDtoList getAllRecordsForCurrentAccount() {
	final Account currentAccount = accountService.getCurrentAccount();
	final List<PendingQueue> allPendingQueues = pendingQueueService
		.getAllRecordsForCurrentAccount(currentAccount);
	final AbstractDtoEntityMapper mapper = mapperFactory
		.createInstance(PendingQueueDtoList.class);
	return (PendingQueueDtoList) mapper.unmap(allPendingQueues);
    }
}
