package com.user.service.Impl;

import com.user.service.SendMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendMailServiceImpl implements SendMailService {
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String from;


    @Override
    public void sendSimpleMail(String to, String subject, String content){
        SimpleMailMessage simpleMailMessage =  new SimpleMailMessage();

        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        try{
            javaMailSender.send(simpleMailMessage);
            log.info("from:" + from + "to:" + to + "," +"邮件发送成功");
        }catch (Exception e){
            log.error("from:" + from + "to:" + to + "," +"邮件发送失败");
        }
    }

    @Override
    public void emailFromToTo(String from, String to, String subject, String content){
        SimpleMailMessage simpleMailMessage =  new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try{
            javaMailSender.send(simpleMailMessage);
            log.info("from:" + from + "to:" + to + "," +"邮件发送成功");
        }catch (Exception e){
            log.error("from:" + from + "to:" + to + "," +"邮件发送失败");
        }
    }





}
