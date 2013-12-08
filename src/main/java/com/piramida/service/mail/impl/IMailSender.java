package com.piramida.service.mail.impl;

import javax.mail.MessagingException;

import com.piramida.entity.Email;

public interface IMailSender {

    void sendEmail(final Email email) throws MessagingException;

}
