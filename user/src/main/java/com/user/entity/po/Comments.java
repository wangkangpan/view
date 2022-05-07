package com.user.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Comments {
    private String id;
    private String title;
    private String comments;
    //jwt
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    //jwt
    private String userId;
    private String parentId;
//    ==============================================

    private String parentUserName;

    public Comments(){}
    public Comments(String id, String comments, Date createTime, String userName){
        this.id = id;
        this.comments = comments;
        this.createTime = createTime;
        this.userName = userName;
    }
    public Comments(String id,String title,String comments,String userName,
                    Date createTime, String userId, String parentId,
                    String parentUserName
                    ){
        this.id = id;
        this.title = title;
        this.comments = comments;
        this.userName = userName;
        this.createTime = createTime;
        this.userId = userId;
        this.parentId = parentId;
        this.parentUserName = parentUserName;
    }


}
