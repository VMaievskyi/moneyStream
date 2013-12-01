package com.piramida.cronjobs.queue;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.piramida.dao.pendingqueue.PendingQueueDao;
import com.piramida.dao.queue.QueueDao;
import com.piramida.entity.PendingQueue;
import com.piramida.entity.Queue;

@Service("lostPendingQueueCleaner")
public class LostPendingQueueCleaner {

    private static Logger LOG = LoggerFactory
	    .getLogger(LostPendingQueueCleaner.class);

    @Autowired
    private PendingQueueDao pendingQueueDao;
    @Autowired
    private QueueDao queueDao;

    @Scheduled(cron = "${oldQueue.cron}")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void cleanLeavedPendingQueue() {
	if (LOG.isInfoEnabled()) {
	    LOG.info("about to run scheduled job: delete old queues");
	}
	final Calendar cal = Calendar.getInstance();
	cal.add(Calendar.HOUR_OF_DAY, -2);

	final List<PendingQueue> oldQueues = pendingQueueDao
		.findInnactiveOlderThen(cal);
	if (oldQueues != null) {
	    if (LOG.isInfoEnabled()) {
		LOG.info("about to delete {} queues", oldQueues.size());
	    }
	    for (final PendingQueue oldQueue : oldQueues) {
		oldQueue.getQueue().getPendingQueues().remove(oldQueue);
		final Queue garantedQueue = oldQueue.getGarantedQueue();
		queueDao.delete(garantedQueue);
	    }
	}

    }

}
