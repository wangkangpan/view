package com.mail;

import com.user.service.SendMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


public class MailTest {
    @Autowired
    SendMailService sendMailService;

    @Test
    public void test(){
        sendMailService.sendSimpleMail("WKPSDL@outlook.com", "测试", "这是一封测试邮件");

    }
}
