package com.piramida.dao.pendingqueue;

import java.sql.Timestamp;
import java.util.List;

import com.piramida.dao.GenericDao;
import com.piramida.entity.PendingQueue;

public interface PendingQueueDao extends GenericDao<PendingQueue> {

    List<PendingQueue> getAllPending();

    PendingQueue getBySecureId(String receiptId);

    List<PendingQueue> findInnactiveOlderThen(Timestamp valueOf);

}
