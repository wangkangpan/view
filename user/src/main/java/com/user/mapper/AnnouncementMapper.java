package com.user.mapper;

import com.user.entity.po.Announcement;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface AnnouncementMapper {

    List<Announcement> selectAll();
}
