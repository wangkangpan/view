package com.user.service;

import com.user.entity.po.Announcement;
import com.user.entity.vo.Page;

import java.util.List;

public interface CommentsService {

    List<Announcement> getAllAnnouncements(Page page);

    Integer getCountAnnouncements();

    Announcement getAnnouncementById(String id);
}
