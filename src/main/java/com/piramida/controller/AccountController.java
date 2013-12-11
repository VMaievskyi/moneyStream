package com.piramida.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.piramida.controller.exception.AccountOperationException;
import com.piramida.entity.dto.AccountDto;
import com.piramida.entity.dto.MessageDto;
import com.piramida.facade.account.AccountFacade;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountFacade accountFacade;

    @ResponseBody
    @RequestMapping(value = "/activation/{activationString}")
    public MessageDto activateAccount(
	    @PathVariable final String activationString)
	    throws AccountOperationException {
	accountFacade.activateAccount(activationString);
	return new MessageDto("account.activated");
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public void createAccount(
	    @ModelAttribute("account") final AccountDto account)
	    throws AccountOperationException {
	if (account.getId() == null) {
	    accountFacade.createAccount(account);
	} else {
	    throw new AccountOperationException("forbidden to update account");
	}
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String signUp(final Model model) {
	final AccountDto account = new AccountDto();
	prepopulate(account);
	model.addAttribute("account", account);
	return "signUp";
    }

    private void prepopulate(final AccountDto account) {
	final List<String> supportedWallets = Lists.newArrayList("Qiwi",
		"WebMoney");
	for (int i = 0; i < supportedWallets.size(); i++) {
	    account.getWallets().get(i).setWalletType(supportedWallets.get(i));
	}

    }

    public AccountFacade getAccountFacade() {
	return accountFacade;
    }

    public void setAccountFacade(final AccountFacade accountFacade) {
	this.accountFacade = accountFacade;
    }

}
