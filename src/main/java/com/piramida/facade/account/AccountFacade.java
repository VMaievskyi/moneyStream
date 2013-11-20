package com.piramida.facade.account;

import com.piramida.entity.Account;
import com.piramida.entity.dto.AccountDto;

public interface AccountFacade {

    void activateAccount(String string);

    Account createAccount(AccountDto account);

    void updateAccount(Account account);

}
