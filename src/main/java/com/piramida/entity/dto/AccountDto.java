package com.piramida.entity.dto;

import java.io.Serializable;
import java.util.List;

import com.piramida.entity.ActivationStatus;

public class AccountDto implements Serializable {

    private Integer id;
    private String email;
    private String password;
    private ActivationStatus status;
    private String activationString;
    private List<Integer> queuesIds;
    private List<WalletDto> wallets;

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

    public List<Integer> getQueuesIds() {
	return queuesIds;
    }

    public void setQueuesIds(final List<Integer> queuesIds) {
	this.queuesIds = queuesIds;
    }

    public List<WalletDto> getWallets() {
	return wallets;
    }

    public void setWallets(final List<WalletDto> wallets) {
	this.wallets = wallets;
    }

}
