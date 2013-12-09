package com.piramida.service.mail.persister.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring3.SpringTemplateEngine;

import com.piramida.dao.email.EmailDao;
import com.piramida.entity.Account;
import com.piramida.entity.Email;
import com.piramida.service.mail.persister.IPersister;

@Service("accountMailPersister")
public class AccountMailPersister implements IPersister {

    @Autowired
    private EmailDao emailDao;

    @Value(value = "${mail.accountHtml}")
    private String emailPattern;

    @Autowired
    @Qualifier("templateEngine")
    private SpringTemplateEngine templateEngine;

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public void persistMail(final Account account) {

	final String htmlContent = formText(account);
	final Email email = createEmailObject(account, htmlContent);

	emailDao.save(email);
    }

    private Email createEmailObject(final Account account,
	    final String htmlContent) {
	final Email email = new Email();
	email.setRecepient(account.getEmail());
	email.setText(htmlContent);
	return email;
    }

    private String formText(final Account account) {
	final Context ctx = new Context();
	ctx.setVariable("activationLink", account.getActivationString());
	final String htmlContent = this.templateEngine.process(emailPattern,
		ctx);
	return htmlContent;
    }

}
