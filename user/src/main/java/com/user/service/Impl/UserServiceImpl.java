package com.user.service.Impl;

import com.api.util.CreateString;
import com.user.util.PatternUtil;
import com.user.mapper.UserMapper;
import com.user.pojo.User;
import com.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.Date;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;

//    @Autowired
//    SendMailService sendMailService;

    private final RedisTemplate<String,String> redisTemplate;

    private final PatternUtil patternUtil;

    private final Integer AttachID = 6;

    private User getUserByUserName(String userName){
        return userMapper.selectUserByUserName(userName);
    }

    public boolean checkPasswordByUserName(String userName, String password){
        User user = this.getUserByUserName(userName);

        return user.getPassword().equals(password);

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

        return patternUtil.isEmail(user.getEmail())
                ||user.getPassword() == null
                || user.getTelephone() == null
                || user.getUserName() == null
                || !user.getCertificate().equals(
                    redisTemplate.opsForValue().get(user.getEmail())
                );

    }

}
