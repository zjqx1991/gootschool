package com.gootschool.pojo.search;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Document(indexName = "goods", type = "docs", shards = 1, replicas = 0)
public class Goods implements Serializable {


    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    private String id;

    @ApiModelProperty(value = "搜索关键词")
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String searchWord;

    @ApiModelProperty(value = "课程讲师id")
    private String teacherId;

    @ApiModelProperty(value = "课程讲师名称")
    private String teacherName;

    @ApiModelProperty(value = "课程专业一级ID")
    private String subjectId1;

    @ApiModelProperty(value = "课程专业一级名称")
    private String subjectName1;

    @ApiModelProperty(value = "课程专业二级ID")
    private String subjectId2;

    @ApiModelProperty(value = "课程专业二级名称")
    private String subjectName2;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "销售数量")
    private Long buyCount;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "乐观锁")
    @Version
    private Long version;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;
}
