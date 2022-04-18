package com.user.mapper;

import com.user.entity.po.Announcement;
import com.user.entity.vo.Page;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface AnnouncementMapper {

    List<Announcement> selectAll(Page page);

    Integer selectCountFromAnnouncement();

    Announcement selectAnnouncementById(String id);
}
