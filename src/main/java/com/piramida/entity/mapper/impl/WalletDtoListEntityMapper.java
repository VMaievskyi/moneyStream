package com.piramida.entity.mapper.impl;

import java.util.Set;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;
import com.piramida.entity.Wallet;
import com.piramida.entity.dto.WalletDto;
import com.piramida.entity.dto.WalletDtoList;
import com.piramida.entity.mapper.factory.MapperFactory;

public class WalletDtoListEntityMapper extends
	AbstractDtoEntityMapper<WalletDtoList, Set<Wallet>> {

    @Autowired
    private MapperFactory factory;

    @Override
    public Set<Wallet> map(final WalletDtoList source) {
	Validate.notNull(source, "source was null");
	final Set<WalletDto> walletsDto = source.getWallets();
	final Set<Wallet> wallets = Sets.newHashSet();
	final AbstractDtoEntityMapper mapper = factory
		.createInstance(Wallet.class);

	if (walletsDto != null) {
	    for (final WalletDto dto : walletsDto) {
		final Wallet wallet = (Wallet) mapper.map(dto);
		wallets.add(wallet);
	    }
	}
	return wallets;
    }
}
