package com.piramida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.piramida.controller.exception.AccountOperationException;
import com.piramida.entity.dto.AccountDto;
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
    @ResponseBody
    public void createAccount(@RequestBody final AccountDto account)
	    throws AccountOperationException {
	if (account.getId() == null) {
	    accountFacade.createAccount(account);
	} else {
	    throw new AccountOperationException("forbidden to update account");
	}
    }

    public AccountFacade getAccountFacade() {
	return accountFacade;
    }

    public void setAccountFacade(final AccountFacade accountFacade) {
	this.accountFacade = accountFacade;
    }

}
