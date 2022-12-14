package com.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.api.util.JwtUtils;
import com.user.entity.po.Announcement;
import com.user.entity.po.Comments;
import com.user.entity.vo.Page;
import com.user.service.CommentsService;
import com.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("comments")
public class CommentsController {

    private final CommentsService commentsService;



    @PostMapping("/getAnnouncements")
    public List<Announcement> getAnnouncements(Page page){
        return commentsService.getAllAnnouncements(page);
    }

    @PostMapping("/getCountsAnnouncements")
    public Integer getCountsAnnouncements(){
        return commentsService.getCountAnnouncements();
    }

    @PostMapping("/getAnnouncementById")
    public Announcement getAnnouncementById(@RequestParam(required = false, defaultValue = "1") String id){
        return commentsService.getAnnouncementById(id);
    }
    @PostMapping("addAnnouncement")
    public Result<Integer> addAnnouncement(Announcement announcement){
        return new Result<>(Result.Success,"添加公告成功",commentsService.addAnnouncement(announcement));
    }

//    ==============================================================================
//    @PostMapping("addComment")
//    public Result<Integer> addComment(Comment comment){
//        return new Result<>(Result.Success,"添加公告成功",commentsService.addAnnouncement(announcement));
//    }
    @PostMapping("/getComments")
    public List<Comments> getComments(Page page){
        return commentsService.getAllComments(page);
    }

    @PostMapping("/getCountsComments")
    public Integer getCountsComments(){
        return commentsService.getCountComments();
    }

    @PostMapping("/replies")
    public Result<?> getReplies(Integer id){
        List<Comments> res = commentsService.getAllReplies(id);
        return new Result<>(Result.Success,"查询回复信息成功",res);
    }

    @PostMapping
    @RequestMapping("/comment")
    public Result<?> getComment(Integer id){
        Comments comments = commentsService.getCommentById(id);
        return new Result<>(Result.Success, "查询评论信息成功",comments);
    }

    @PostMapping
    @RequestMapping("/addReply")
    public Result<?> addReply(Comments comments, String token){

        comments.setUserId(JwtUtils.getClaim(token,"userId"));
        commentsService.addCommentIsReply(comments);
        return new Result<>(Result.Success, "添加/回复成功",comments);
    }
}
