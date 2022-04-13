package com.user.controller;


import com.api.util.CreateString;
import com.user.util.PatternUtil;
import com.user.entity.User;
import com.user.service.SendMailService;
import com.user.service.UserService;
import com.api.util.JwtUtils;
import com.user.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
public class LoaderController {


    @Autowired
    UserService userService;
    @Autowired
    SendMailService sendMailService;

    @Autowired

    RedisTemplate redisTemplate;
    @Autowired
    PatternUtil patternUtil;

    @Value("${spring.mail.register.subject}")
    private String subject;
    @Value("${spring.mail.register.content}")
    private String content;
    @Value("${spring.mail.register.timeout}")
    private Integer timeout;



    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result verifyPassword(String userName, String password){

        if(userService.checkPasswordByUserName(userName, password)){
            return new Result(Result.Success, new JwtUtils().sign(userName));
        }
        return new Result(Result.DataBaseDefault, "账号或密码错误");

    }

    @RequestMapping(value = "/emailVerify", method = RequestMethod.POST)
    public Result emailVerify(String email, HttpServletRequest request) throws Exception{

            //禁止频繁访问
            String ip = request.getRemoteAddr();
            if(!patternUtil.isEmail(email) || redisTemplate.hasKey(ip))
                return new Result(Result.VerifyEmailMessageDefault, "发送失败");


            ValueOperations valueOperations = redisTemplate.opsForValue();

            //存储近期IP
            valueOperations.set(ip, ip, timeout, TimeUnit.SECONDS);

            String verifyCode = CreateString.getRandomString(6);
            //key 保存10分钟
            valueOperations.set(email, verifyCode, timeout, TimeUnit.SECONDS);

            sendMailService.sendSimpleMail(
                    email,
                    subject,
                    content + verifyCode);
            return new Result(Result.Success, "发送成功");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result addUser(User user){

        if(userService.injectRegister(user) < 1) {
            return new Result(Result.DataBaseDefault, "注册失败");

        }else {
            return new Result(Result.Success, "注册成功");

        }
    }


}
