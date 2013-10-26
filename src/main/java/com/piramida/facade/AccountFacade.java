package com.piramida.facade;

import com.piramida.entity.Account;
import com.piramida.entity.dto.AccountDto;

public interface AccountFacade {

    void activateAccount(String string);

    void createAccount(AccountDto account);

    void updateAccount(Account account);

}
