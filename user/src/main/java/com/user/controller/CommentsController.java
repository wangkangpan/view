package com.user.controller;

import com.user.entity.po.Announcement;
import com.user.entity.po.Comments;
import com.user.entity.vo.Page;
import com.user.service.CommentsService;
import com.user.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
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
        Integer num = commentsService.addAnnouncement(announcement);
        return new Result<Integer>(Result.Success,"success",num);
    }

//    ==============================================================================
    @PostMapping("/getComments")
    public List<Comments> getComments(Page page){
        return commentsService.getAllComments(page);
    }

    @PostMapping("/getCountsComments")
    public Integer getCountsComments(){
        return commentsService.getCountComments();
    }


}
