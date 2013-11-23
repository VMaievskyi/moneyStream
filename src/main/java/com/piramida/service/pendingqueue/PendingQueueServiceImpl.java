package com.piramida.service.pendingqueue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.piramida.dao.pendingqueue.PendingQueueDao;
import com.piramida.entity.Account;
import com.piramida.entity.PendingQueue;

@Component("pendingQueueService")
public class PendingQueueServiceImpl implements PendinQueueService {

    @Autowired
    private PendingQueueDao pendingQueueDao;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public List<PendingQueue> getAllRecordsForCurrentAccount(
	    final Account currentAccount) {
	return pendingQueueDao.getAllPending();
    }

}
