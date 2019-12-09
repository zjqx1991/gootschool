package com.gootschool.common.response;


import com.gootschool.common.code.RevanCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel(value = "全局统一返回结果")
public class RevanResponse {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回状态码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    public RevanResponse() {
    }

    //链式编程
    public static RevanResponse ok() {
        RevanResponse response = new RevanResponse();
        response.setSuccess(RevanCodeEnum.SUCCESS.getSuccess());
        response.setCode(RevanCodeEnum.SUCCESS.getCode());
        response.setMessage(RevanCodeEnum.SUCCESS.getMessage());
        return response;
    }

    public static RevanResponse error() {
        RevanResponse response = new RevanResponse();
        response.setSuccess(RevanCodeEnum.FAIL.getSuccess());
        response.setCode(RevanCodeEnum.FAIL.getCode());
        response.setMessage(RevanCodeEnum.FAIL.getMessage());
        return response;
    }

    public static RevanResponse response(RevanCodeEnum revanCodeEnum) {
        RevanResponse response = new RevanResponse();
        response.setSuccess(revanCodeEnum.getSuccess());
        response.setCode(revanCodeEnum.getCode());
        response.setMessage(revanCodeEnum.getMessage());
        return response;
    }

    public RevanResponse success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public RevanResponse code(Integer code) {
        this.setCode(code);
        return this;
    }

    public RevanResponse message(String message) {
        this.setMessage(message);
        return this;
    }

    public RevanResponse data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public RevanResponse date(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

}
