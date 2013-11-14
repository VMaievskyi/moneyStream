package com.piramida.entity.dto;

import java.util.Set;

public class WalletDtoList {

    private Set<WalletDto> wallets;

    public WalletDtoList() {
	super();
    }

    public WalletDtoList(final Set<WalletDto> wallets) {
	super();
	this.wallets = wallets;
    }

    public Set<WalletDto> getWallets() {
	return wallets;
    }

    public void setWallets(final Set<WalletDto> wallets) {
	this.wallets = wallets;
    }

}
