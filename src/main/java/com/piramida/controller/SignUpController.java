package com.piramida.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.piramida.entity.Account;

@Controller
@RequestMapping(value = "/signup")
public class SignUpController {

    @RequestMapping(method = RequestMethod.POST)
    public String signup(final Account account) {
	return null;
    }
}
