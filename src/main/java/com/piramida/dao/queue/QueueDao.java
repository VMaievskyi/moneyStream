package com.piramida.dao.queue;

import java.util.List;

import com.piramida.dao.GenericDao;
import com.piramida.entity.Queue;

public interface QueueDao extends GenericDao<Queue> {

    void switchPositions(Queue queue, Queue secondRow);

    Queue getFirst(final String queueType);

    Queue findById(Integer id);

    List<Queue> findAllRange(int istartIndex, int countToReturn);

}
