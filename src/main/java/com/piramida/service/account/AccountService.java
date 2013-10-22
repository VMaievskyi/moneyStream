package com.piramida.service.account;

import com.piramida.entity.Account;

public interface AccountService {

    void createUserAccount(Account account);

    void activateUserAccount(String userActivationString);

    void deactivateAccount(Account account);

    Account findByEmail(String email);
}
