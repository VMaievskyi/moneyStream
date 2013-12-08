package com.piramida.service.mail.impl;

import com.piramida.entity.Account;

public interface IMailSender {

    void sendEmail(final Account account);

}
