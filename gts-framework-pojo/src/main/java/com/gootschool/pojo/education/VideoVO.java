package com.gootschool.pojo.education;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "小节信息")
public class VideoVO implements Serializable {

    private String id;
    private String title;
    private Boolean free;
}
