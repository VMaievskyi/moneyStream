package com.piramida.service.account;

import com.piramida.entity.Account;

public interface AccountService {

    void createUserAccount(Account account);

    void activateUserAccount(String userActivationString);

    void deactivateAccount(Account account);

    void updateUserAccount(final Account account);

    Account findByEmail(String email);

    Account findById(Integer accountId);
}
