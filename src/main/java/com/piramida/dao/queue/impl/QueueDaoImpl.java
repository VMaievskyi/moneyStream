package com.piramida.dao.queue.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.piramida.dao.AbstractGenegicDao;
import com.piramida.dao.queue.QueueDao;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.Queue;

public class QueueDaoImpl extends AbstractGenegicDao<Queue> implements QueueDao {

    private static final int ONE = 1;

    @Override
    protected String getEntityName() {
	return "Queue";
    }

    @Override
    public List<Queue> findAll() {
	final Query allQueues = getSessionFactory().getCurrentSession()
		.createQuery(findAll + " order by position");
	return allQueues.list();
    }

    @Override
    public void switchPositions(final Queue queue, final Queue secondRow) {
	final Integer position = queue.getPosition();
	queue.setPosition(secondRow.getPosition());
	secondRow.setPosition(position);

	save(queue);
	save(secondRow);
    }

    @Override
    public Queue getFirst(final String queueType) {
	final Query oneRowQuery = getSessionFactory()
		.getCurrentSession()
		.createQuery(
			"from "
				+ getEntityName()
				+ " as e where queueType=:queueType and e.pendingQueues.size < e.requiredPaymentCount and status = :status");
	oneRowQuery.setParameter("queueType", queueType);
	oneRowQuery.setParameter("status", ActivationStatus.ACTIVE);
	oneRowQuery.setMaxResults(ONE);
	return (Queue) oneRowQuery.uniqueResult();

    }

    @Override
    public Queue findById(final Integer id) {
	final Session currentSession = getSessionFactory().getCurrentSession();
	final Query byIdQuery = currentSession
		.createQuery("from Queue where id=:id");
	byIdQuery.setParameter("id", id);
	return (Queue) byIdQuery.uniqueResult();
    }

    @Override
    public List<Queue> findAllRange(final String queueType,
	    final int istartIndex, final int countToReturn) {
	final Session currentSession = getSessionFactory().getCurrentSession();
	final Query rangedSearch = currentSession
		.createQuery(
			"from " + getEntityName()
				+ " where queueType=:queueType")
		.setFirstResult(istartIndex)
		.setMaxResults(countToReturn + istartIndex);
	rangedSearch.setString("queueType", queueType);
	return rangedSearch.list();
    }

    @Override
    public Integer deleteQueueWithStatus(final ActivationStatus status) {
	final Session currentSession = getSessionFactory().getCurrentSession();
	final Query deleteQuery = currentSession
		.createQuery("delete * from :entity where status=:status");
	deleteQuery.setString("entity", getEntityName());
	deleteQuery.setString("status", status.toString());
	return deleteQuery.executeUpdate();

    }
}
