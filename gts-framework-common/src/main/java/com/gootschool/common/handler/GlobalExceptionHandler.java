package com.gootschool.common.handler;

import com.gootschool.common.response.RevanResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice(basePackages = "com.gootschool")
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RevanResponse error(Exception e) {
        e.printStackTrace();
        return RevanResponse.error();
    }
}
