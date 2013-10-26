package com.piramida.entity.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.piramida.entity.ActivationStatus;

public class AccountDto implements Serializable {
    private Integer id;
    private String email;
    private String password;
    private ActivationStatus status;
    private String activationString;
    private Set<Integer> queuesIds = new HashSet<Integer>();
    private Set<WalletDto> wallets = new HashSet<WalletDto>();

    public Integer getId() {
	return id;
    }

    public void setId(final Integer id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(final String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(final String password) {
	this.password = password;
    }

    public ActivationStatus getStatus() {
	return status;
    }

    public void setStatus(final ActivationStatus status) {
	this.status = status;
    }

    public String getActivationString() {
	return activationString;
    }

    public void setActivationString(final String activationString) {
	this.activationString = activationString;
    }

    public Set<Integer> getQueuesIds() {
	return queuesIds;
    }

    public Set<WalletDto> getWallets() {
	return wallets;
    }

    public void setWallets(final Set<WalletDto> wallets) {
	this.wallets = wallets;
    }

    public void setQueuesIds(final Set<Integer> queuesIds) {
	this.queuesIds = queuesIds;
    }

}
