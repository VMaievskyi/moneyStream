package com.piramida.cronjobs.queue;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.piramida.dao.pendingqueue.PendingQueueDao;
import com.piramida.dao.queue.QueueDao;
import com.piramida.entity.PendingQueue;
import com.piramida.entity.Queue;

public class LostPendingQueueCleaner {

    @Autowired
    private PendingQueueDao pendingQueueDao;
    @Autowired
    private QueueDao queueDao;

    // TODO: THIS SHIT SHOULD BE TESTED!
    @Scheduled(cron = "0 0 0/2 * * ?")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void cleanLeavedPendingQueue() {
	final Calendar cal = Calendar.getInstance();
	cal.add(Calendar.HOUR_OF_DAY, -2);

	final List<PendingQueue> oldQueues = pendingQueueDao
		.findInnactiveOlderThen(cal);
	for (final PendingQueue oldQueue : oldQueues) {
	    final Queue garantedQueue = oldQueue.getGarantedQueue();
	    queueDao.delete(garantedQueue);
	}

    }

}
