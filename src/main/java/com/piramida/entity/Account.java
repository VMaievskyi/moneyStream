package com.piramida.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA. User: slava Date: 10/20/13 Time: 2:49 PM To
 * change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Account", schema = "", catalog = "hibnatedb")
@Entity
public class Account {
    private int id;
    private String email;
    private String password;
    private String status;

    @javax.persistence.Column(name = "id")
    @Id
    public int getId() {
	return id;
    }

    public void setId(final int id) {
	this.id = id;
    }

    @javax.persistence.Column(name = "email")
    @Basic
    public String getEmail() {
	return email;
    }

    public void setEmail(final String email) {
	this.email = email;
    }

    @javax.persistence.Column(name = "password")
    @Basic
    public String getPassword() {
	return password;
    }

    public void setPassword(final String password) {
	this.password = password;
    }

    @javax.persistence.Column(name = "status")
    @Basic
    public String getStatus() {
	return status;
    }

    public void setStatus(final String status) {
	this.status = status;
    }

    @Override
    public boolean equals(final Object o) {
	if (this == o) {
	    return true;
	}
	if (o == null || getClass() != o.getClass()) {
	    return false;
	}

	final Account account = (Account) o;

	if (id != account.id) {
	    return false;
	}
	if (email != null ? !email.equals(account.email)
		: account.email != null) {
	    return false;
	}
	if (password != null ? !password.equals(account.password)
		: account.password != null) {
	    return false;
	}
	if (status != null ? !status.equals(account.status)
		: account.status != null) {
	    return false;
	}

	return true;
    }

    @Override
    public int hashCode() {
	int result = id;
	result = 31 * result + (email != null ? email.hashCode() : 0);
	result = 31 * result + (password != null ? password.hashCode() : 0);
	result = 31 * result + (status != null ? status.hashCode() : 0);
	return result;
    }
}
