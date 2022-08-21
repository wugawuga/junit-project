package com.wuga.junit_project.util;

import org.springframework.stereotype.Component;

@Component
public class MailSenderSutb implements MailSender {

    @Override
    public boolean send() {

        return true;
    }
    
}
