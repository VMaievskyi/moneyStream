package com.piramida.dao.account;

import com.piramida.dao.GenericDao;
import com.piramida.entity.Account;

public interface AccountDao extends GenericDao<Account> {

    Account findByEmail(String email);

}
