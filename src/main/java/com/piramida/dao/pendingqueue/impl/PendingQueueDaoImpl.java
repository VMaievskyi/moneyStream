package com.piramida.dao.pendingqueue.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.piramida.dao.AbstractGenegicDao;
import com.piramida.dao.pendingqueue.PendingQueueDao;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.PendingQueue;

@Repository("pendingQueueDao")
public class PendingQueueDaoImpl extends AbstractGenegicDao<PendingQueue>
	implements PendingQueueDao {

    @Override
    public List<PendingQueue> getAllPending() {
	final Query getAllQuery = getSessionFactory().getCurrentSession()
		.createQuery(
			"from " + getEntityName()
				+ " where status in :statusList");
	getAllQuery.setParameterList("statusList", Lists.newArrayList(
		ActivationStatus.PENDING, ActivationStatus.REJECTED));
	return getAllQuery.list();
    }

    @Override
    protected String getEntityName() {
	return "PendingQueue";
    }

    @Override
    public PendingQueue getBySecureId(final String receiptId) {
	final Query getById = getSessionFactory().getCurrentSession()
		.createQuery(
			"from " + getEntityName()
				+ " where secureId=:securedId");
	getById.setParameter("securedId", receiptId);
	return (PendingQueue) getById.uniqueResult();
    }

    @Override
    public List<PendingQueue> findInnactiveOlderThen(final Timestamp time) {
	final Query getOldQuery = getSessionFactory()
		.getCurrentSession()
		.createQuery(
			"from "
				+ getEntityName()
				+ " where (creationDate - :currentDate) < :range");

	getOldQuery.setTimestamp("currentDate", DateTime.now().toDate());
	getOldQuery.setTimestamp("range", time);

	return getOldQuery.list();
    }
}
