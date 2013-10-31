package com.piramida.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.piramida.entity.Account;
import com.piramida.service.account.AccountService;

public class AccountSecurityService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(final String username)
	    throws UsernameNotFoundException {
	final Account account = accountService.findByEmail(username);
	if (account == null) {
	    throw new UsernameNotFoundException("User with email " + username
		    + " not found");
	}

	return account;
    }

}
