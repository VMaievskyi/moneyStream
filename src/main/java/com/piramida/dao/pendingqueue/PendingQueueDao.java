package com.piramida.dao.pendingqueue;

import java.util.Calendar;
import java.util.List;

import com.piramida.dao.GenericDao;
import com.piramida.entity.PendingQueue;

public interface PendingQueueDao extends GenericDao<PendingQueue> {

    List<PendingQueue> getAllPending();

    PendingQueue getBySecureId(String receiptId);

    List<PendingQueue> findInnactiveOlderThen(Calendar time);

}
