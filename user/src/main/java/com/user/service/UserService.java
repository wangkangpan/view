package com.user.service;


import com.user.entity.User;

public interface UserService {

    boolean checkPasswordByUserName(String userName, String password);

    String getRelationshipById(String id);

    Integer injectRegister(User user);

}
