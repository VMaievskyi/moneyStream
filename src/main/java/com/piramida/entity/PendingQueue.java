package com.piramida.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "PendingQueue", schema = "", catalog = "hibnatedb")
@Entity
public class PendingQueue {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Queue_id", referencedColumnName = "id")
    private Queue queue;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Queue_Account_id", referencedColumnName = "id")
    private Account account;

    public Integer getId() {
	return id;
    }

    public void setId(final Integer id) {
	this.id = id;
    }

    public Queue getQueue() {
	return queue;
    }

    public void setQueue(final Queue queue) {
	this.queue = queue;
    }

    public Account getAccount() {
	return account;
    }

    public void setAccount(final Account account) {
	this.account = account;
    }

}
