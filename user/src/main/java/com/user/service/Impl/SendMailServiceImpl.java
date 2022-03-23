package com.user.service.Impl;

import com.user.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class SendMailServiceImpl implements SendMailService {
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String from;


    public void sendSimpleMail(String to, String subject, String content){
        SimpleMailMessage simpleMailMessage =  new SimpleMailMessage();

        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        try{
            javaMailSender.send(simpleMailMessage);
            System.out.println("success");
        }catch (Exception e){
            System.out.println("default");
        }
    }





}
