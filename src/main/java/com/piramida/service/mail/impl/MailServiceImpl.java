package com.piramida.service.mail.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.piramida.entity.Account;
import com.piramida.entity.EmailType;
import com.piramida.service.mail.MailService;

public class MailServiceImpl implements MailService {

    @Autowired
    @Qualifier("mailSender")
    private MailSender mailSender;
    @Autowired
    @Qualifier("templateMessage")
    private SimpleMailMessage simpleMailMessage;

    @Override
    public void sendEmail(final EmailType emailType, final Account account) {

	simpleMailMessage.setTo(account.getEmail());
	simpleMailMessage.setSubject("activateion");
	simpleMailMessage
		.setText("please folow: http://localhost:8080/moneyStream-0.0.1-SNAPSHOT/account/activation/"
			+ account.getActivationString());
	mailSender.send(simpleMailMessage);
    }

    public MailSender getMailSender() {
	return mailSender;
    }

    public void setMailSender(final MailSender mailSender) {
	this.mailSender = mailSender;
    }

    public SimpleMailMessage getSimpleMailMessage() {
	return simpleMailMessage;
    }

    public void setSimpleMailMessage(final SimpleMailMessage simpleMailMessage) {
	this.simpleMailMessage = simpleMailMessage;
    }

}
