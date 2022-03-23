package com.script.Filter;

import com.api.util.JwtUtils;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

//filter(过滤器)作用于在interceptor(拦截器)之前
@Order(1)
@WebFilter(urlPatterns = {"/script/*"})
public class LoadFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws NullPointerException {
        //函数getParameter 取参数 | 而函数getAttribute是从session中取

        String token = servletRequest.getParameter("token");
        Object username = new JwtUtils().verify(token);
        if(username == null){
            System.out.printf("错误");
        }
    }

    @Override
    public void destroy() {

    }
}
