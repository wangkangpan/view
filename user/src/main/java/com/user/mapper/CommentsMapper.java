package com.user.mapper;

import com.user.entity.po.Comments;
import com.user.entity.vo.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsMapper {

    List<Comments> selectAllComments(Page page);

    Integer selectCountFromComments();

}
