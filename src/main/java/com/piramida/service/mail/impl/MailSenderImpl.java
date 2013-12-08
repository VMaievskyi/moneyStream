package com.piramida.service.mail.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.piramida.entity.Email;

@Service("mailSenderImpl")
public class MailSenderImpl implements IMailSender {

    private static final Logger LOG = LoggerFactory
	    .getLogger(MailSenderImpl.class);

    @Autowired
    @Qualifier("mailSender")
    private JavaMailSender mailSender;
    @Value(value = "${mail.from}")
    private String fromAddress;

    @Override
    public void sendEmail(final Email email) throws MessagingException {
	final MimeMessage mimeMessage = mailSender.createMimeMessage();
	MimeMessageHelper mimeMessageHelper;
	mimeMessageHelper = new MimeMessageHelper(mimeMessage, Boolean.TRUE,
		"UTF-8");

	mimeMessageHelper.setTo(email.getRecepient());
	mimeMessageHelper.setText(email.getText(), Boolean.TRUE);

	mailSender.send(mimeMessage);
    }

}
