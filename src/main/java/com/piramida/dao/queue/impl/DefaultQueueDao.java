package com.piramida.dao.queue.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.piramida.dao.AbstractGenegicDao;
import com.piramida.dao.queue.QueueDao;
import com.piramida.entity.Queue;

public class DefaultQueueDao extends AbstractGenegicDao<Queue> implements
	QueueDao {

    private static final int ONE = 1;

    @Override
    protected String getEntityName() {
	return "Queue";
    }

    public void switchPositions(final Queue queue, final Queue secondRow) {
	final int tempPosition = queue.getPosition();
	updatePositionForRow(secondRow.getPosition(), queue.getId());
	updatePositionForRow(tempPosition, secondRow.getId());
    }

    public Queue getFirst() {
	final Query oneRowQuery = getSessionFactory().getCurrentSession()
		.createQuery("from " + getEntityName()).setMaxResults(ONE);
	return (Queue) oneRowQuery.uniqueResult();

    }

    private void updatePositionForRow(final int newPosition, final int currentId) {
	final Session currentSession = getSessionFactory().getCurrentSession();
	final Query firstQuery = currentSession.createQuery("Update "
		+ getEntityName() + "SET position = :pos WHERE id= :idv");
	firstQuery.setParameter("pos", newPosition);
	firstQuery.setParameter("idv", currentId);
	firstQuery.executeUpdate();
    }
}
