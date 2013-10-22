package com.piramida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.piramida.controller.exception.AccountOperationException;
import com.piramida.entity.Account;
import com.piramida.facade.AccountFacade;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountFacade accountFacade;

    @RequestMapping(value = "/activation/{activationString}")
    public void activateAccount(@PathVariable final String activationString)
	    throws AccountOperationException {
	accountFacade.activateAccount(activationString);

    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public void createAccount(@RequestBody final Account account)
	    throws AccountOperationException {

    }

    public AccountFacade getAccountFacade() {
	return accountFacade;
    }

    public void setAccountFacade(final AccountFacade accountFacade) {
	this.accountFacade = accountFacade;
    }

}
