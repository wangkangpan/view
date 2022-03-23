package com.user.pojo;

import lombok.Data;

@Data
public class User {

    String id;
    String userName;
    String password;
    String email;
    String telephone;
    String certificate;


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", certificate='" + certificate + '\'' +
                '}';
    }
}
