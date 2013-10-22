package com.piramida.facade;

import com.piramida.entity.Account;

public interface AccountFacade {

    void activateAccount(String string);

    void createAccount(Account account);
}
