package com.user.exception.handler;

import com.user.exception.base.BaseException;
import com.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class UnityExceptionHandler extends BaseException {
    

    @ExceptionHandler(value = Exception.class)
    public Result<?> doHandler(Exception e){
        log.error(e.getMessage());
        return new Result<>(Result.UnKnownDefault,"服务器异常",null);
    }
}
