package com.user.entity.po;

import lombok.Data;

@Data
public class Comments {
    private String id;
    private String title;
    private String comments;
    private String userName;
    private String creatTime;
    private String userId;
}
