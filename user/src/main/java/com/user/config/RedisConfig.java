package com.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${redis.ip}")
    String ip = "127.0.1";
    @Value("${redis.port}")
    Integer port = 6379;

    //redis连接工厂
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){

        //参数远程Redis IP或者集群
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(ip, port);

        //集群 new RedisClusterConfiguration
        return  new JedisConnectionFactory(redisStandaloneConfiguration);
    }


    //api, 线程安全
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //Redis指定序列化
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());

        return redisTemplate;
    }
}
