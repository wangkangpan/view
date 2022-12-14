package com.user.service.Impl;

import com.api.util.CreateString;
import com.user.util.PatternUtil;
import com.user.mapper.UserMapper;
import com.user.entity.User;
import com.user.service.UserService;
import com.vo.Result;
import lombok.RequiredArgsConstructor;


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

    public Result<?> checkPasswordByUserName(String userName, String password){
        if(userName == null || password == null || userName == "" || password == ""){
            return new Result<>(Result.UnLoad,"账号或者密码为空",null);

        }
        User user = this.getUserByUserName(userName);

        if(user == null || !password.equals(user.getPassword())){
            return new Result<>(Result.UnLoad,"账号或者密码错误",null);
        }

//        return user.getPassword().equals(password);
        return new Result<>(Result.Success,"登陆成功",user);

    }

    @Override
    public User getUser(String id) {
        return userMapper.selectUserByid(id);
    }

    @Override
    public Integer setUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public String getRelationshipById(String id) {
        return userMapper.selectUserByid(id).getTelephone();
    }


    @Override
    public Integer injectRegister(User user) {
        if(!legalRegister(user))
            return 0;

        user.setUserId(
                new Date().getTime() + '-' + CreateString.getRandomString(AttachID)
        );
        user.setCreateTime(new Date());
        return userMapper.insertUserIDAndCertificate(user);
    }

    @Override
    public Integer removeUser(String id) {
        return userMapper.deleteUser(id);
    }


    private boolean legalRegister(User user){

        //0&1 = 0; 0|1 = 1
        return patternUtil.isEmail(user.getEmail())
                && user.getPassword() != null
                && user.getTelephone() != null
                && user.getUserName() != null
                && user.getCertificate().equals(
                    redisTemplate.opsForValue().get(user.getEmail())
                );

    }

}
