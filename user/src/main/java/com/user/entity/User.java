package com.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    String id;
    String userId;
    String userName;
    String password;
    String email;
    String telephone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createTime;
    String certificate;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", createTime=" + createTime +
                ", certificate='" + certificate + '\'' +
                '}';
    }
}
