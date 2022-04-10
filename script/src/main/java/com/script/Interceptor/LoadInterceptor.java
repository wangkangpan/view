package com.script.Interceptor;

import com.api.util.JwtUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class LoadInterceptor implements HandlerInterceptor {


    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * 函数getParameter 取参数 | 而函数getAttribute是从session中取
     */
    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse response, Object handler) {
        //统一拦截
        String token = servletRequest.getParameter("token");
        Object username = new JwtUtils().verify(token);
        if(username != null){
            return true;
        }
        return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作

    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//         System.out.println("执行了TestInterceptor的postHandle方法");
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("执行了TestInterceptor的afterCompletion方法");
    }



}
