package com.gootschool.common.code;

import lombok.Getter;

@Getter
public enum RevanCodeEnum {

    SUCCESS(true, 0, "成功"),
    FAIL(false, 500, "系统错误"),
    PARAM_FAIL(false, 1, "参数错误"),

    // 上传文件
    UPLOAD_FAIL(false, 1, "参数错误"),

    ;

    private Boolean success;
    private Integer code;
    private String message;

    RevanCodeEnum() {
    }

    RevanCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
