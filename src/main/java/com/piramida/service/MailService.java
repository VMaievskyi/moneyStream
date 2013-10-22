package com.piramida.service;

import com.piramida.entity.Account;
import com.piramida.entity.EmailType;

public interface MailService {

    void sendEmail(EmailType emailType, Account account);
}
