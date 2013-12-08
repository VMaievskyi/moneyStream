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
import org.thymeleaf.context.Context;
import org.thymeleaf.spring3.SpringTemplateEngine;

import com.piramida.entity.Account;

@Service("accountOperationInformer")
public class AccountOperationMailSender implements IMailSender {

    private static final Logger LOG = LoggerFactory
	    .getLogger(AccountOperationMailSender.class);

    @Autowired
    @Qualifier("templateEngine")
    private SpringTemplateEngine templateEngine;
    @Autowired
    @Qualifier("mailSender")
    private JavaMailSender mailSender;
    @Value(value = "${mail.from}")
    private String fromAddress;
    @Value(value = "${mail.accountHtml}")
    private String emailPattern;

    @Override
    public void sendEmail(final Account account) {
	final MimeMessage mimeMessage = mailSender.createMimeMessage();
	MimeMessageHelper mimeMessageHelper;
	try {
	    mimeMessageHelper = new MimeMessageHelper(mimeMessage,
		    Boolean.TRUE, "UTF-8");
	    final Context ctx = new Context();
	    ctx.setVariable("activationLink", account.getActivationString());
	    final String htmlContent = this.templateEngine.process(
		    emailPattern, ctx);

	    mimeMessageHelper.setTo(account.getEmail());
	    mimeMessageHelper.setText(htmlContent, Boolean.TRUE);

	    mailSender.send(mimeMessage);
	} catch (final MessagingException e) {
	    if (LOG.isErrorEnabled()) {
		LOG.error("failed to send email to account {}",
			account.getEmail());
	    }
	}

    }

    public String getEmailPattern() {
	return emailPattern;
    }

    public void setEmailPattern(final String emailPattern) {
	this.emailPattern = emailPattern;
    }

}
