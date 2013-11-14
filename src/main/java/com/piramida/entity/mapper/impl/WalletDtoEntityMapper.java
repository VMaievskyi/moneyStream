package com.piramida.entity.mapper.impl;

import com.piramida.entity.Wallet;
import com.piramida.entity.dto.WalletDto;

public class WalletDtoEntityMapper extends
		AbstractDtoEntityMapper<WalletDto, Wallet> {

	@Override
	public Wallet map(final WalletDto source) {
		final Wallet target = new Wallet();
		target.setId(source.getId());
		target.setWaletNumber(source.getWaletNumber());
		target.setWalletType(source.getWalletType());
		return target;
	}
}
