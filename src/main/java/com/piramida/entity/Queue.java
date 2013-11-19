package com.piramida.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Queue {

    private Integer id;

    private String queueType;
    private Account account;
    private Integer requiredPaymentCount;
    private Integer position;
    private Set<PendingQueue> pendingQueues = new HashSet<PendingQueue>();
    private PendingQueue garantedPendingQueue;
    private ActivationStatus status;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    public Integer getId() {
	return id;
    }

    public void setId(final Integer id) {
	this.id = id;
    }

    @Column(name = "position")
    public Integer getPosition() {
	return position;
    }

    public void setPosition(final Integer position) {
	this.position = position;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Account_id", referencedColumnName = "id", insertable = true)
    public Account getAccount() {
	return account;
    }

    public String getQueueType() {
	return queueType;
    }

    public void setQueueType(final String queueType) {
	this.queueType = queueType;
    }

    public void setAccount(final Account account) {
	this.account = account;
    }

    @Column(name = "requiredPaymentCount")
    @Basic
    public Integer getRequiredPaymentCount() {
	return requiredPaymentCount;
    }

    public void setRequiredPaymentCount(final Integer requiredPaymentCount) {
	this.requiredPaymentCount = requiredPaymentCount;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "queue")
    public Set<PendingQueue> getPendingQueues() {
	return pendingQueues;
    }

    public void setPendingQueues(final Set<PendingQueue> pendingQueues) {
	this.pendingQueues = pendingQueues;
    }

    @OneToOne(mappedBy = "garantedQueue", cascade = CascadeType.ALL)
    public PendingQueue getGarantedPendingQueue() {
	return garantedPendingQueue;
    }

    public void setGarantedPendingQueue(final PendingQueue garantedPendingQueue) {
	this.garantedPendingQueue = garantedPendingQueue;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public ActivationStatus getStatus() {
	return status;
    }

    public void setStatus(final ActivationStatus status) {
	this.status = status;
    }

}
