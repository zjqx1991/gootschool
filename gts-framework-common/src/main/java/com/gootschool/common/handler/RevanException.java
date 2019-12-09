package com.gootschool.common.handler;

import com.gootschool.common.code.RevanCodeEnum;
import lombok.Getter;

@Getter
public class RevanException extends RuntimeException{

    // 异常
    private RevanCodeEnum codeEnum;

    public RevanException(RevanCodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }
}
