package com.user.entity.po;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Announcement implements Serializable {
    private String title;
    private String content;
    private Date createTime;
}
