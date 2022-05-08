package com.user.mapper;

import com.user.entity.po.Comments;
import com.user.entity.vo.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsMapper {

    List<Comments> selectAllComments(Page page);

    Comments selectCommentsById(Integer id);

    Integer selectCountFromComments();

    List<Comments> selectCommentsReplyFromComments(Integer id);

    Integer insertComments(Comments comments);

    Integer insertRelationUserComments(String userId,Integer commentsId);

}
