package com.piramida.service.wallet.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piramida.dao.account.AccountDao;
import com.piramida.entity.Account;
import com.piramida.entity.Wallet;
import com.piramida.service.wallet.WalletService;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void updateWallets(final Set<Wallet> wallets, final Account account) {
	account.setWallets(wallets);
	accountDao.save(account);
    }

}
