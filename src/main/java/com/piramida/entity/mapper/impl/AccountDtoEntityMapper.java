package com.piramida.entity.mapper.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.piramida.entity.Account;
import com.piramida.entity.Wallet;
import com.piramida.entity.dto.AccountDto;
import com.piramida.entity.dto.WalletDto;
import com.piramida.entity.mapper.factory.MapperFactory;

public class AccountDtoEntityMapper extends
	AbstractDtoEntityMapper<AccountDto, Account> {

    @Autowired
    @Qualifier("mapperFactory")
    private MapperFactory mapperFactory;

    public Account map(final AccountDto source) {
	final Account target = new Account();
	target.setActivationString(source.getActivationString());
	target.setEmail(source.getEmail());
	target.setId(source.getId());
	target.setPassword(source.getPassword());
	target.setStatus(source.getStatus());
	mapWallets(source, target);
	mapQueues(source, target);
	return target;
    }

    private void mapQueues(final AccountDto source, final Account target) {
	final List<Integer> queuesIds = source.getQueuesIds();
	if (queuesIds != null) {
	    for (final int id : queuesIds) {

	    }
	}
    }

    private void mapWallets(final AccountDto source, final Account target) {
	final List<WalletDto> walletsDto = source.getWallets();
	final Set<Wallet> wallets = new HashSet<Wallet>();
	if (walletsDto != null) {
	    final AbstractDtoEntityMapper mapper = mapperFactory
		    .createInstance(WalletDto.class);
	    for (final WalletDto wallet : walletsDto) {
		wallets.add((Wallet) mapper.map(wallet));
	    }
	}
	target.setWallets(wallets);

    }

}
