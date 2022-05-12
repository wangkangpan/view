package com.user.service;


import com.user.entity.User;
import com.vo.Result;

public interface UserService {

    Result<?> checkPasswordByUserName(String userName, String password);


    String getRelationshipById(String id);

    Integer injectRegister(User user);

}
