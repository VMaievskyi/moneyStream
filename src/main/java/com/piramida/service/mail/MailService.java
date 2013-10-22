package com.piramida.service.mail;

import com.piramida.entity.Account;
import com.piramida.entity.EmailType;

public interface MailService {

    void sendEmail(EmailType emailType, Account account);
}
