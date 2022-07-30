package com.script.config.Web;

import com.script.Interceptor.LoadInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebLoginConfig implements WebMvcConfigurer {

    @Autowired
    private LoadInterceptor loadInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(loadInterceptor);
        registration.addPathPatterns("/do-script/script/**");      //所有路径都被拦截
        registration.excludePathPatterns(                //添加不拦截路径
                "/do-script/rpc/**",  // 放行RPC 服务接口
                "/*",            //登录
                "/**/*.html",            //html静态资源
                "/**/*.js",              //js静态资源
                "/**/*.css",             //css静态资源
                "/**/*.woff",
                "/**/*.ttf"
        );
    }


}
