package com.gootschool.pojo.education.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "Teacher条件查询对象", description = "讲师查询对象封装")
public class TeacherQuery implements Serializable {

    @ApiModelProperty(value = "讲师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1：高级讲师 2：首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间")
    private String end;

    public TeacherQuery() {
    }
}
