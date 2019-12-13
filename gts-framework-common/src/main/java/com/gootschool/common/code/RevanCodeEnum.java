package com.gootschool.common.code;

import lombok.Getter;

@Getter
public enum RevanCodeEnum {

    SUCCESS(true, 0, "成功"),
    FAIL(false, 500, "系统错误"),
    PARAM_FAIL(false, 1, "参数错误"),

    // 上传文件
    UPLOAD_FAIL(false, 1, "参数错误"),
    UPLOAD_EXCEL_FAIL(false, 1, "Excel数据导入错误"),

    // 课目
    SUBJECT_NOT_DELETE(false, 1, "存在子分类，请删除子分类"),
    SUBJECT_SAVEED(false, 1, "课程已存在"),

    // 课程
    COURSE_SAVE_FAIL(false, 1, "课程信息保存失败"),
    COURSE_UPDATE_FAIL(false, 1, "课程信息更新失败"),


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
