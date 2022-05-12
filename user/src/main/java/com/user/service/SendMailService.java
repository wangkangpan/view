package com.user.service;

public interface SendMailService {

    void sendSimpleMail(String to, String subject, String content);

    void emailFromToTo(String from, String to, String subject, String content);
}
