package com.user.service;

import com.sun.corba.se.impl.protocol.giopmsgheaders.ReplyMessage;
import com.user.entity.po.Announcement;
import com.user.entity.po.Comments;
import com.user.entity.vo.Page;

import java.util.List;

public interface CommentsService {

    List<Announcement> getAllAnnouncements(Page page);

    Integer getCountAnnouncements();

    Announcement getAnnouncementById(String id);

    Integer addAnnouncement(Announcement announcement);

    //    ==============================================================
    List<Comments>  getAllComments(Page page);

    Integer getCountComments();

    Comments getCommentById(Integer id);

    //根据父id获得该评论的回复
    List<Comments> getReplies(Integer id);

    //根据父id获得该评论下的回复（树）
    List<Comments> getAllReplies(Integer id);

    Integer addCommentIsReply(Comments comments);


}
