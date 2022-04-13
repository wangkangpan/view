package com.user.controller;

import com.user.entity.po.Announcement;
import com.user.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("comments")
public class CommentsController {

    private final CommentsService commentsService;

    @RequestMapping("/getAnnouncements")
    public List<Announcement> getAnnouncements(){
        return commentsService.getAllAnnouncements();
    }
}
