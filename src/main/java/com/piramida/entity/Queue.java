package com.piramida.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Queue {
    private Integer id;

    private String queueType;
    private Account account;
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

}
