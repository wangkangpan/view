package com.user.controller;

import com.user.entity.po.Announcement;
import com.user.entity.vo.Page;
import com.user.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("comments")
public class CommentsController {

    private final CommentsService commentsService;

    @RequestMapping("/getAnnouncements")
    public List<Announcement> getAnnouncements(Page page){
        return commentsService.getAllAnnouncements(page);
    }

    @RequestMapping("/getCountsAnnouncements")
    public Integer getCountsAnnouncements(){
        return commentsService.getCountAnnouncements();
    }

    @RequestMapping("/getAnnouncementById")
    public Announcement getAnnouncementById(String id){
        return commentsService.getAnnouncementById(id);
    }
}
