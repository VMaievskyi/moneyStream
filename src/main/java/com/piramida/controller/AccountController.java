package com.piramida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.piramida.controller.exception.AccountOperationException;
import com.piramida.entity.dto.AccountDto;
import com.piramida.entity.dto.manager.impl.AccountDtoManager;
import com.piramida.facade.account.AccountFacade;

@Controller
@RequestMapping(value = "/account")
@SessionAttributes("account")
public class AccountController {

    @Autowired
    private AccountFacade accountFacade;
    @Autowired
    @Qualifier("accountValidator")
    private Validator accountValidator;
    @Autowired
    private AccountDtoManager accountDtoManager;

    @RequestMapping(value = "/activation/{activationString}")
    public String activateAccount(@PathVariable final String activationString)
	    throws AccountOperationException {
	accountFacade.activateAccount(activationString);
	return "redirect:/queue";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createAccount(
	    @ModelAttribute("account") final AccountDto account,
	    final BindingResult result, final SessionStatus status)
	    throws AccountOperationException {
	String toGo = null;
	accountValidator.validate(account, result);
	if (result.hasErrors()) {
	    toGo = "signUp";
	} else {
	    doCreateAccount(account, result);
	    status.setComplete();
	    toGo = "activationPending";
	}
	return toGo;
    }

    private void doCreateAccount(final AccountDto account,
	    final BindingResult errors) throws AccountOperationException {
	try {
	    accountFacade.createAccount(account);
	} catch (final IllegalArgumentException exc) {
	    errors.rejectValue("email", "duplicate", "email is already used");
	}
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String signUp(final Model model) {
	model.addAttribute("account", accountDtoManager.createDto());
	return "signUp";
    }

}
