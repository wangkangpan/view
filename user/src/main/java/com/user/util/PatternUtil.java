package com.user.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PatternUtil {

    @Value("${regex.email}")
    String emailRegex;
    @Value("${regex.url.separtor}")
    String urlSepartor;

    public boolean isEmail(String s){
        if(Pattern.matches(emailRegex, s))
            return true;

        return false;
    }

    public String[] separtor(String url){
        return url.split(urlSepartor);
    }


//    public static void main(String[] args){
////        String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=1;https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=2";
////        String[] strs=url.split(";");
////        for(String s : strs){
////            System.out.println(s);
////        }
//
//    }
}
