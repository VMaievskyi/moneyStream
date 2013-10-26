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
    private final Set<Integer> queuesIds = new HashSet<Integer>();
    private final Set<Integer> walletsIds = new HashSet<Integer>();

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

    public Set<Integer> getWalletsIds() {
	return walletsIds;
    }

}
