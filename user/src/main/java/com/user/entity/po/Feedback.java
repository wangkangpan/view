package com.user.entity.po;


import lombok.Data;

@Data
public class Feedback {

    private String subject;
    private String content;
    private String relationship;
    private String userName;
}
