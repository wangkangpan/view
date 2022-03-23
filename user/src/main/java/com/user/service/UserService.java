package com.user.service;


import com.user.pojo.User;

public interface UserService {

    boolean checkPasswordByUserName(String userName, String password);



    Integer injectRegister(User user);

}
