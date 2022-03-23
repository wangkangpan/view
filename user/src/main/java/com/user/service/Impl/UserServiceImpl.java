package com.user.service.Impl;

import com.api.util.CreateString;
import com.user.util.PatternUtil;
import com.user.mapper.UserMapper;
import com.user.pojo.User;
import com.user.service.SendMailService;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.Date;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    @Autowired
    SendMailService sendMailService;

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    PatternUtil patternUtil;

    Integer AttachID = 6;

    private User getUserByUserName(String userName){
        return userMapper.selectUserByUserName(userName);
    }

    public boolean checkPasswordByUserName(String userName, String password){
        User user = this.getUserByUserName(userName);

        if(!user.getPassword().equals(password))
            return false;

        return true;
    }


    @Override
    public Integer injectRegister(User user) {
        if(!legalRegister(user))
            return 0;

        user.setId(
                new Date().getTime() + '-' + CreateString.getRandomString(AttachID)
        );

        return userMapper.insertUserIDAndCertificate(user);
    }



    private boolean legalRegister(User user){

        if(!patternUtil.isEmail(user.getEmail())
                ||user.getPassword() == null
                || user.getTelephone() == null
                || user.getUserName() == null
                || !user.getCertificate().equals(
                    redisTemplate.opsForValue().get(user.getEmail())
                )
        ){
            return false;
        }
        return true;
    }

}
