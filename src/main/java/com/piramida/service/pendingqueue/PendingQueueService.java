package com.piramida.service.pendingqueue;

import java.util.List;

import com.piramida.entity.Account;
import com.piramida.entity.PendingQueue;

public interface PendingQueueService {

    List<PendingQueue> getAllRecordsForCurrentAccount(Account currentAccount);

    void approvePendingQueue(String receiptId);

    void rejectPendingQueue(String receiptId);

}
