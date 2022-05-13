package com.user.mapper;

import com.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectUserByUserName(String userName);

    User selectUserByid(String id);
    //信息完全插入一个User
    Integer insertUser(User user);

    Integer updateUser(User user);

    Integer insertUserIDAndCertificate(User user);

//    Integer updateUserByIDAndCertificate(User user);
    Integer deleteUser(String id);

}
