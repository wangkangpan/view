package com.user.service;


import com.user.entity.User;
import com.vo.Result;

public interface UserService {

    Result<?> checkPasswordByUserName(String userName, String password);

    User getUser(String id);

    Integer setUser(User user);

    String getRelationshipById(String id);

    Integer injectRegister(User user);

    Integer removeUser(String id);

}
