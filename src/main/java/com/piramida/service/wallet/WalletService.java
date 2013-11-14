package com.piramida.service.wallet;

import java.util.Set;

import com.piramida.entity.Account;
import com.piramida.entity.Wallet;

public interface WalletService {

    void updateWallets(Set<Wallet> wallets, Account account);
}
