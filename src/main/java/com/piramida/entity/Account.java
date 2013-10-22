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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Table(name = "Account", schema = "", catalog = "hibnatedb")
@Entity
public class Account {
    private Integer id;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    private ActivationStatus status;
    private String activationString;
    private Set<Queue> queues = new HashSet<Queue>();
    private Set<Wallet> wallets = new HashSet<Wallet>();

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    public Integer getId() {
	return id;
    }

    public void setId(final Integer id) {
	this.id = id;
    }

    @Column(name = "email")
    @Basic
    public String getEmail() {
	return email;
    }

    public void setEmail(final String email) {
	this.email = email;
    }

    @Column(name = "password")
    @Basic
    public String getPassword() {
	return password;
    }

    public void setPassword(final String password) {
	this.password = password;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public ActivationStatus getStatus() {
	return status;
    }

    public void setStatus(final ActivationStatus status) {
	this.status = status;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
    public Set<Queue> getQueues() {
	return queues;
    }

    public void setQueues(final Set<Queue> queues) {
	this.queues = queues;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
    public Set<Wallet> getWallets() {
	return wallets;
    }

    public void setWallets(final Set<Wallet> wallets) {
	this.wallets = wallets;
    }

    public String getActivationString() {
	return activationString;
    }

    public void setActivationString(final String activationString) {
	this.activationString = activationString;
    }

}
