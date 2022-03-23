package com.user.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PatternUtil {

    @Value("${regex.email}")
    String emailRegex;

    public boolean isEmail(String s){
        if(Pattern.matches(emailRegex, s))
            return true;

        return false;
    }
}
