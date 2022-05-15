package com.script.exception.handler;

import com.script.exception.base.BaseException;
import com.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//ExceptionHandler, 方法注解, 作用于 Controller 级别. ExceptionHandler 注解为一个 Controler 定义一个异常处理器.
//ControllerAdvice, 类注解, 作用于 整个 Spring 工程. ControllerAdvice 注解定义了一个全局的异常处理器.
@Slf4j
@ControllerAdvice
public class UnityExceptionHandler extends BaseException {


    @ExceptionHandler(value = Exception.class)
    public Result<?> doHandler(Exception e){
        log.error(e.getMessage());
//        在static/error下存在404会自动跳转，这里做个样子，返回没意义
        return new Result<>(Result.UnKnownDefault,"服务器异常",null);
    }
}
