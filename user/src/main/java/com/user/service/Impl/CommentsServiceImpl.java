package com.user.service.Impl;

import com.user.entity.po.Announcement;
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
    public List<Announcement> getAllAnnouncements() {
        return announcementMapper.selectAll();
    }
}
