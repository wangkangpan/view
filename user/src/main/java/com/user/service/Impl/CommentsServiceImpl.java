package com.user.service.Impl;

import com.user.entity.po.Announcement;
import com.user.entity.vo.Page;
import com.user.mapper.AnnouncementMapper;
import com.user.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final AnnouncementMapper announcementMapper;

    @Override
    public List<Announcement> getAllAnnouncements(Page page) {
        page.setStartAndEnd(page.getPageNo(),page.getPageSize());
        return announcementMapper.selectAll(page);


    }

    @Override
    public Integer getCountAnnouncements() {
        return announcementMapper.selectCountFromAnnouncement();
    }

    @Override
    public Announcement getAnnouncementById(String id) {
        return announcementMapper.selectAnnouncementById(id);
    }

}
