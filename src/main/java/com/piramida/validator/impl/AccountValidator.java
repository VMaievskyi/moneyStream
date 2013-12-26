package com.piramida.validator.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.piramida.entity.dto.AccountDto;
import com.piramida.entity.dto.WalletDto;
import com.piramida.service.account.AccountService;

@Component
public class AccountValidator implements Validator {

    @Autowired
    private AccountService accountService;

    @Override
    public void validate(final Object test, final Errors errors) {
	final AccountDto target = (AccountDto) test;
	checkEmail(target, errors);
	checkIfPasswordNotEmpty(target.getPassword(), errors);
	checkIfAtLeastOneWalletAdded(target.getWallets(), errors);
    }

    private void checkEmail(final AccountDto target, final Errors errors) {
	final String email = target.getEmail();
	checkIfEmailNotEmpty(email, errors);
	checkIfEmailExists(email, errors);
	checkEmailPattern(email, errors);
    }

    private void checkEmailPattern(final String email, final Errors errors) {
	final boolean isValidEmail = Pattern.matches(
		"[A-Za-z0-9\\._%+-]{1,64}@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
		email);
	if (!isValidEmail) {
	    errors.rejectValue("email", "wrongFormat",
		    "email should be like user@host.com");
	}

    }

    private void checkIfAtLeastOneWalletAdded(final List<WalletDto> wallets,
	    final Errors errors) {
	if (walletsAreNotPopulated(wallets)) {
	    errors.rejectValue("wallets", "required", "at least one required");
	}

    }

    private boolean walletsAreNotPopulated(final List<WalletDto> wallets) {
	boolean result = true;
	for (final WalletDto dto : wallets) {
	    final String waletNumber = dto.getWaletNumber();
	    if (!StringUtils.isEmpty(waletNumber)) {
		result = false;
		break;
	    }
	}

	return result;
    }

    private void checkIfPasswordNotEmpty(final String password,
	    final Errors errors) {
	if (StringUtils.isEmpty(password)) {
	    errors.rejectValue("password", "required", "field is mandatory");
	}
    }

    private void checkIfEmailExists(final String email, final Errors errors) {
	if (accountService.findByEmail(email) != null) {
	    errors.rejectValue("email", "duplicate", "email is already used");
	}

    }

    private void checkIfEmailNotEmpty(final String email, final Errors errors) {
	if (StringUtils.isEmpty(email)) {
	    errors.rejectValue("email", "required", "field is mandatory");
	}
    }

    @Override
    public boolean supports(final Class<?> clazz) {
	return AccountDto.class.equals(clazz);
    }

}
