package com.redis;


import com.user.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.regex.Pattern;

@SpringJUnitConfig(classes = RedisConfig.class)
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test(){
        //String
        ValueOperations valueOperations = redisTemplate.opsForValue();


        valueOperations.set("name","nidie");

        System.out.println(valueOperations.get("name"));
    }

    @Test
    public void test2(){
        boolean s = Pattern.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", "WKPSDL@outlook.com");
        System.out.println(String.valueOf(s));
    }
}
