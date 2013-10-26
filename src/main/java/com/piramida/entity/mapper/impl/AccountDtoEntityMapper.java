package com.piramida.entity.mapper.impl;

import java.util.Set;

import com.piramida.entity.Account;
import com.piramida.entity.dto.AccountDto;

public class AccountDtoEntityMapper extends
	AbstractDtoEntityMapper<AccountDto, Account> {

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
	final Set<Integer> queuesIds = source.getQueuesIds();
	if (queuesIds != null) {
	    for (final int id : queuesIds) {

	    }
	}
    }

    private void mapWallets(final AccountDto source, final Account target) {
	// TODO Auto-generated method stub

    }

}
