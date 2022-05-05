package com.user.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class Comments {
    private String id;
    private String title;
    private String comments;
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String creatTime;
    private String userId;
}
