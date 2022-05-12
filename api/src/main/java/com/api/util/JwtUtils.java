package com.api.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 * @编辑 **王康攀**
 * @时间 **20211113**
 *
 */
@Slf4j
public class JwtUtils {
    //过期时间
    private static final long EXPIRE_TIME = 60 * 60 * 1000;//ms
    //私钥
    private static final String TOKEN_SECRET = "zhishiwodexiao99";

    /**
     * 生成令牌，60分钟过期
     * @param **username**
     * @param **password**
     * @return
     */
    public static String sign(String userName, String id) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)//1、头部
                    .withClaim("userName", userName)//2、payload负载
                    .withClaim("id",id)
                    .withExpiresAt(date)//指定过期时间
                    .sign(algorithm);//3、签名
        } catch (Exception e) {
            e.printStackTrace();
            return "token生成失败";
        }
    }

    /**
     * 检验token是否正确
     * @param **token**
     * @return
     */
    public static String verify(String token){
        try {
            //创建验证对象
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            //验证
            DecodedJWT jwt = verifier.verify(token);
            //取payload,取多个使用jwt.getClaims()-->Object.asString
            String userName = jwt.getClaim("userName").asString();
            return userName;
//            错误的令牌
        }catch(JWTDecodeException e){
            return null;
//            空的令牌
        }catch(NullPointerException e){
            return null;
        }

    }
    /**
     * 返回负载存储
     * @param **token**
     * @param **key**
     * @return
     */
    public static String getClaim(String token,String key){
        try {
            //创建验证对象
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();

            DecodedJWT jwt = verifier.verify(token);
            //取payload,取多个使用jwt.getClaims()-->Object.asString
            String value = jwt.getClaim(key).asString();
            return value;
//            错误的令牌
        }catch(JWTDecodeException e){
            log.error("JWTDecodeException:" + e.getMessage());
            return null;
//            空的令牌
        }catch(NullPointerException e){
            log.error("NullPointerException:" + e.getMessage());
            return null;
        }

    }

}
