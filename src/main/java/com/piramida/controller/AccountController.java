package com.piramida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.piramida.facade.AccountFacade;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountFacade accountFacade;

    @RequestMapping(value = "/activation/{activationString}")
    public void activateAccount(@PathVariable final String activationString) {
	accountFacade.activateAccount(activationString);

    }

    public AccountFacade getAccountFacade() {
	return accountFacade;
    }

    public void setAccountFacade(final AccountFacade accountFacade) {
	this.accountFacade = accountFacade;
    }

}
