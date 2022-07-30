package com.script.Interceptor;

import com.api.util.JwtUtils;
import com.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
@Slf4j
@Component
public class LoadInterceptor implements HandlerInterceptor {


    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * 函数getParameter 取参数 | 而函数getAttribute是从session中取
     */
    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse response, Object handler) {
        //统一拦截
//        String token = servletRequest.getParameter("token");
        String token = JwtUtils.sign("die","2","1");
        Object username = JwtUtils.verify(token);
        if(username != null){
            return true;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            Result<String> res = new Result(Result.UnLoad,"未登录",token);
            response.getWriter().append(res.toJson().toJSONString());
        }catch (Exception e) {

            log.error(e.getMessage());
            return false;
        }
        return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作

    }




}
