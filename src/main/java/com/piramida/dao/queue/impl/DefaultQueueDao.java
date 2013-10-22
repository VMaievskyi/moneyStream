package com.piramida.dao.queue.impl;

import com.piramida.dao.AbstractGenegicDao;
import com.piramida.dao.queue.QueueDao;
import com.piramida.entity.Queue;

public class DefaultQueueDao extends AbstractGenegicDao<Queue> implements
	QueueDao {

    @Override
    protected String getEntityName() {
	return "Queue";
    }

}
