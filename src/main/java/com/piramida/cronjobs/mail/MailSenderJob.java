package com.piramida.cronjobs.mail;

import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.piramida.dao.email.EmailDao;
import com.piramida.entity.Email;
import com.piramida.service.mail.impl.IMailSender;

@Service("mailSenderJob")
public class MailSenderJob {

    private static Logger LOG = LoggerFactory.getLogger(MailSenderJob.class);

    @Autowired
    private EmailDao emailDao;
    @Value("${mail.maxRetryCount}")
    private Integer maxRetryCount;

    private IMailSender mailSender;

    @Scheduled(cron = "${sendEmail.cron}")
    public void sendEmails() {
	final List<Email> allEmails = emailDao.findAll();
	if (allEmails != null) {
	    sendAllEmail(allEmails);
	}

    }

    private void sendAllEmail(final List<Email> allEmails) {
	for (final Email email : allEmails) {
	    sendEmail(email);

	}
    }

    private void sendEmail(final Email email) {
	try {
	    mailSender.sendEmail(email);
	    removeEmail(email);
	} catch (final MessagingException e) {
	    final boolean shouldRetry = email.getTryCounter() < maxRetryCount;
	    if (LOG.isDebugEnabled()) {
		LOG.debug("failed to send email to {} should retry={}",
			email.getRecepient(), shouldRetry);
	    }
	    handleRetry(email, shouldRetry);
	}
    }

    private void handleRetry(final Email email, final boolean shouldRetry) {
	if (shouldRetry) {
	    email.setTryCounter(email.getTryCounter() + 1);
	    emailDao.save(email);
	} else {
	    removeEmail(email);
	}
    }

    private void removeEmail(final Email email) {
	if (LOG.isDebugEnabled()) {
	    LOG.debug("about to delete email to {}", email.getRecepient());
	}
	emailDao.delete(email);
    }
}
