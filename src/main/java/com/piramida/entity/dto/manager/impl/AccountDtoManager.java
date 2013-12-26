package com.piramida.entity.dto.manager.impl;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.piramida.entity.dto.AccountDto;
import com.piramida.entity.dto.manager.DtoManager;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AccountDtoManager implements DtoManager<AccountDto> {

    @Override
    public AccountDto createDto() {
	final AccountDto account = new AccountDto();
	final List<String> supportedWallets = Lists.newArrayList("Qiwi",
		"WebMoney");
	for (int i = 0; i < supportedWallets.size(); i++) {
	    account.getWallets().get(i).setWalletType(supportedWallets.get(i));
	}
	return account;

    }

}
