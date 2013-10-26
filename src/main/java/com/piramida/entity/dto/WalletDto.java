package com.piramida.entity.dto;

public class WalletDto {
    private Integer id;
    private String waletNumber;
    private String walletType;
    private Integer ownerId;

    public Integer getId() {
	return id;
    }

    public void setId(final Integer id) {
	this.id = id;
    }

    public String getWaletNumber() {
	return waletNumber;
    }

    public void setWaletNumber(final String waletNumber) {
	this.waletNumber = waletNumber;
    }

    public String getWalletType() {
	return walletType;
    }

    public void setWalletType(final String walletType) {
	this.walletType = walletType;
    }

    public Integer getOwnerId() {
	return ownerId;
    }

    public void setOwnerId(final Integer ownerId) {
	this.ownerId = ownerId;
    }

}
