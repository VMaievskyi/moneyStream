package com.piramida.entity;

import javax.persistence.Basic;
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

@Entity
public class Queue {
    private Integer id;

    private QueueType queueType;
    private Integer position;
    private ActivationStatus status;
    private Account account;
    private Integer paymentCount;
    private Integer requiredPaymentCount;

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
    @Basic
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

    @Column(name = "queueType")
    @Enumerated(EnumType.STRING)
    public QueueType getQueueType() {
	return queueType;
    }

    public void setQueueType(final QueueType queueType) {
	this.queueType = queueType;
    }

    public void setAccount(final Account account) {
	this.account = account;
    }

    @Column(name = "paymentCount")
    @Basic
    public Integer getPaymentCount() {
	return paymentCount;
    }

    public void setPaymentCount(final Integer paymentCount) {
	this.paymentCount = paymentCount;
    }

    @Column(name = "requiredPaymentCount")
    @Basic
    public Integer getRequiredPaymentCount() {
	return requiredPaymentCount;
    }

    public void setRequiredPaymentCount(final Integer requiredPaymentCount) {
	this.requiredPaymentCount = requiredPaymentCount;
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
