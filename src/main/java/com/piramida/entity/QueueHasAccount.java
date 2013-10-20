package com.piramida.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA. User: slava Date: 10/20/13 Time: 2:49 PM To
 * change this template use File | Settings | File Templates.
 */
@javax.persistence.IdClass(com.piramida.entity.QueueHasAccountPK.class)
@javax.persistence.Table(name = "Queue_has_Account", schema = "", catalog = "hibnatedb")
@Entity
public class QueueHasAccount {
    private int queueId;

    @javax.persistence.Column(name = "queue_id")
    @Id
    public int getQueueId() {
	return queueId;
    }

    public void setQueueId(final int queueId) {
	this.queueId = queueId;
    }

    private int accountsId;

    @javax.persistence.Column(name = "accounts_id")
    @Id
    public int getAccountsId() {
	return accountsId;
    }

    public void setAccountsId(final int accountsId) {
	this.accountsId = accountsId;
    }

    @Override
    public boolean equals(final Object o) {
	if (this == o) {
	    return true;
	}
	if (o == null || getClass() != o.getClass()) {
	    return false;
	}

	final QueueHasAccount that = (QueueHasAccount) o;

	if (accountsId != that.accountsId) {
	    return false;
	}
	if (queueId != that.queueId) {
	    return false;
	}

	return true;
    }

    @Override
    public int hashCode() {
	int result = queueId;
	result = 31 * result + accountsId;
	return result;
    }
}
