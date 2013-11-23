package com.piramida.service.pendingqueue;

import java.util.List;

import com.piramida.entity.Account;
import com.piramida.entity.PendingQueue;

public interface PendinQueueService {

    List<PendingQueue> getAllRecordsForCurrentAccount(Account currentAccount);

}
