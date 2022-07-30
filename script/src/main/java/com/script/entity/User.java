package com.script.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    Integer id;
    String userId;
    String userName;
    String password;
    String email;
    String telephone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createTime;
    String certificate;

    public void setQuick(Integer id, String userName, String password){
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
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
