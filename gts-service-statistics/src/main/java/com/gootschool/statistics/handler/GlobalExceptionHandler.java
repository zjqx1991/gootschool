package com.gootschool.statistics.handler;

import com.gootschool.common.handler.RevanException;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.common.utils.RevanExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    //1 对所有的异常进行相同的处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RevanResponse error(Exception e) {
        e.printStackTrace();
        log.error(RevanExceptionUtil.getMessage(e));
        return RevanResponse.error().message("出现了异常");
    }


    //3 自定义异常
    @ExceptionHandler(RevanException.class)
    @ResponseBody
    public RevanResponse error(RevanException e) {
        e.printStackTrace();
        log.error(RevanExceptionUtil.getMessage(e));
        return RevanResponse.error(e.getCodeEnum());
    }
}
