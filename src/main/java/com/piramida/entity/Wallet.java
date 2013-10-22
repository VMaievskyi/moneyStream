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

/**
 * Created with IntelliJ IDEA. User: slava Date: 10/20/13 Time: 2:49 PM To
 * change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Wallet", schema = "", catalog = "hibnatedb")
@Entity
public class Wallet {
    private Integer id;
    private String waletNumber;
    private String walletType;
    private Account owner;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    public Integer getId() {
	return id;
    }

    public void setId(final Integer id) {
	this.id = id;
    }

    @Column(name = "walletType")
    @Basic
    public String getWalletType() {
	return walletType;
    }

    public void setWalletType(final String walletType) {
	this.walletType = walletType;
    }

    @Column(name = "waletNumber")
    @Basic
    public String getWaletNumber() {
	return waletNumber;
    }

    public void setWaletNumber(final String waletNumber) {
	this.waletNumber = waletNumber;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", referencedColumnName = "id", insertable = true)
    public Account getOwner() {
	return owner;
    }

    public void setOwner(final Account owner) {
	this.owner = owner;
    }

}
