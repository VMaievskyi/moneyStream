package com.piramida.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: slava
 * Date: 10/20/13
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueueHasAccountPK implements Serializable {
    private int queueId;
    private int accountsId;

@Id@Column(name = "queue_id")
public int getQueueId() {
    return queueId;
}

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    @Id@Column(name = "accounts_id")
    public int getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(int accountsId) {
        this.accountsId = accountsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QueueHasAccountPK that = (QueueHasAccountPK) o;

        if (accountsId != that.accountsId) return false;
        if (queueId != that.queueId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = queueId;
        result = 31 * result + accountsId;
        return result;
}}
