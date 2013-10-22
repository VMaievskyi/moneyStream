package com.piramida.dao.pendingqueue.impl;

import com.piramida.dao.AbstractGenegicDao;
import com.piramida.dao.pendingqueue.PendingQueueDao;
import com.piramida.entity.PendingQueue;

public class DefaultPendingQueueDao extends AbstractGenegicDao<PendingQueue>
	implements PendingQueueDao {

    @Override
    protected String getEntityName() {
	return "PendingQueue";
    }

}
