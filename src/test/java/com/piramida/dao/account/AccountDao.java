package com.piramida.dao.account;

import java.util.List;

import com.piramida.entity.Account;

public interface AccountDao {

    List<Account> findAll();

    void deleteAll();

    void create(Account testAccount);

}
