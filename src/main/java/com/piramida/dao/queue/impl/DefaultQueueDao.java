package com.piramida.dao.queue.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.piramida.dao.AbstractGenegicDao;
import com.piramida.dao.queue.QueueDao;
import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.Queue;

public class DefaultQueueDao extends AbstractGenegicDao<Queue> implements
	QueueDao {

    private static final int ONE = 1;

    @Override
    protected String getEntityName() {
	return "Queue";
    }

    @Override
    public List<Queue> findAll() {
	final Query allQueues = getSessionFactory().getCurrentSession()
		.createQuery(findAll + " order by id");
	return allQueues.list();
    }

    public void switchPositions(final Queue queue, final Queue secondRow) {
	final Account tempPosition = queue.getAccount();
	final Integer paymentCount = queue.getPaymentCount();
	final Integer requiredPaymentCount = queue.getRequiredPaymentCount();

	queue.setAccount(secondRow.getAccount());
	queue.setPaymentCount(secondRow.getPaymentCount());
	queue.setRequiredPaymentCount(secondRow.getRequiredPaymentCount());

	secondRow.setAccount(tempPosition);
	secondRow.setPaymentCount(paymentCount);
	secondRow.setRequiredPaymentCount(paymentCount);

	save(queue);
	save(secondRow);
    }

    public Queue getFirst(final String queueType) {
	final Query oneRowQuery = getSessionFactory()
		.getCurrentSession()
		.createQuery(
			"from "
				+ getEntityName()
				+ " where queueType=:queueType and status=:status");
	oneRowQuery.setParameter("queueType", queueType);
	oneRowQuery.setParameter("status", ActivationStatus.ACTIVE);
	oneRowQuery.setMaxResults(ONE);
	return (Queue) oneRowQuery.uniqueResult();

    }

    public Queue findById(final Integer id) {
	final Session currentSession = getSessionFactory().getCurrentSession();
	final Query byIdQuery = currentSession
		.createQuery("from Queue where id=:id");
	byIdQuery.setParameter("id", id);
	return (Queue) byIdQuery.uniqueResult();
    }

    public List<Queue> findAllRange(final int istartIndex,
	    final int countToReturn) {
	final Session currentSession = getSessionFactory().getCurrentSession();
	final Query rangedSearch = currentSession
		.createQuery("from " + getEntityName())
		.setFirstResult(istartIndex)
		.setMaxResults(countToReturn + istartIndex);
	return rangedSearch.list();
    }

}
