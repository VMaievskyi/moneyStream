package com.piramida.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA. User: slava Date: 10/20/13 Time: 2:49 PM To
 * change this template use File | Settings | File Templates.
 */
@javax.persistence.IdClass(com.piramida.entity.WalletPK.class)
@javax.persistence.Table(name = "Wallet", schema = "", catalog = "hibnatedb")
@Entity
public class Wallet {
    private int id;

    @javax.persistence.Column(name = "id")
    @Id
    public int getId() {
	return id;
    }

    public void setId(final int id) {
	this.id = id;
    }

    private String walletType;

    @javax.persistence.Column(name = "walletType")
    @Basic
    public String getWalletType() {
	return walletType;
    }

    public void setWalletType(final String walletType) {
	this.walletType = walletType;
    }

    private String waletNumber;

    @javax.persistence.Column(name = "waletNumber")
    @Basic
    public String getWaletNumber() {
	return waletNumber;
    }

    public void setWaletNumber(final String waletNumber) {
	this.waletNumber = waletNumber;
    }

    private int owner;

    @javax.persistence.Column(name = "owner")
    @Id
    public int getOwner() {
	return owner;
    }

    public void setOwner(final int owner) {
	this.owner = owner;
    }

    @Override
    public boolean equals(final Object o) {
	if (this == o) {
	    return true;
	}
	if (o == null || getClass() != o.getClass()) {
	    return false;
	}

	final Wallet wallet = (Wallet) o;

	if (id != wallet.id) {
	    return false;
	}
	if (owner != wallet.owner) {
	    return false;
	}
	if (waletNumber != null ? !waletNumber.equals(wallet.waletNumber)
		: wallet.waletNumber != null) {
	    return false;
	}
	if (walletType != null ? !walletType.equals(wallet.walletType)
		: wallet.walletType != null) {
	    return false;
	}

	return true;
    }

    @Override
    public int hashCode() {
	int result = id;
	result = 31 * result + (walletType != null ? walletType.hashCode() : 0);
	result = 31 * result
		+ (waletNumber != null ? waletNumber.hashCode() : 0);
	result = 31 * result + owner;
	return result;
    }
}
