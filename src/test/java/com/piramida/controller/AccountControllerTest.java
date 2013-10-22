package com.piramida.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.piramida.controller.exception.AccountOperationException;
import com.piramida.dao.account.AccountDao;
import com.piramida.service.security.impl.SecurityStringGeneratorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/WEB-INF/appContext.xml" })
public class AccountControllerTest {

    @Autowired
    private SecurityStringGeneratorService securityStringGeneratorService;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private AccountController testInstance;

    @Test
    public void shouldActivateAccount() throws AccountOperationException {

    }

}
