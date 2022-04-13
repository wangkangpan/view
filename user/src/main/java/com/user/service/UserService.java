package com.user.service;


import com.user.entity.User;

public interface UserService {

    boolean checkPasswordByUserName(String userName, String password);



    Integer injectRegister(User user);

}
