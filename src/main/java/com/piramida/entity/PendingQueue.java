package com.piramida.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PendingQueue {

    private Integer id;
    private ActivationStatus status;
    private Date creationDate;
    private Account pendingQueueOwner;
    private Queue queue;
    private Queue garantedQueue;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
	return id;
    }

    public void setId(final Integer id) {
	this.id = id;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public ActivationStatus getStatus() {
	return status;
    }

    public void setStatus(final ActivationStatus status) {
	this.status = status;
    }

    @Column(name = "creationDate")
    @Temporal(TemporalType.DATE)
    public Date getCreationDate() {
	return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
	this.creationDate = creationDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Account_id", referencedColumnName = "id", insertable = true)
    public Account getPendingQueueOwner() {
	return pendingQueueOwner;
    }

    public void setPendingQueueOwner(final Account pendingQueueOwner) {
	this.pendingQueueOwner = pendingQueueOwner;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Queue_id", referencedColumnName = "id", insertable = true)
    public Queue getQueue() {
	return queue;
    }

    public void setQueue(final Queue queue) {
	this.queue = queue;
    }

    @OneToOne
    @PrimaryKeyJoinColumn(name = "GarantedQueue")
    public Queue getGarantedQueue() {
	return garantedQueue;
    }

    public void setGarantedQueue(final Queue garantedQueue) {
	this.garantedQueue = garantedQueue;
    }

}
