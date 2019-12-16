package com.gootschool.common.code;

import lombok.Getter;

@Getter
public enum RevanCodeEnum {

    SUCCESS(true, 0, "成功"),
    FAIL(false, 99999, "系统错误"),
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
    COURSE_PUBLISH_FAIL(false, 1, "课程发布失败"),

    // 课程章节
    CHAPTER_SAVE_FAIL(false, 1, "课程章节保存失败"),
    CHAPTER_UPDATE_FAIL(false, 1, "课程章节更新失败"),
    CHAPTER_REMOVE_FAIL(false, 1, "课程章节删除失败"),


    // 课程小节
    VIDEO_COURSE_CHAPTER_NOT_FOUND(false, 1, "课程id或者课程章节id不存在"),
    VIDEO_REMOVE_FAIL(false, 1, "课程小节删除失败"),
    VIDEO_SAVE_FAIL(false, 1, "课程小节保存失败"),
    VIDEO_UPDATE_FAIL(false, 1, "课程小节更新失败"),
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
