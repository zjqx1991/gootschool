package com.gootschool.pojo.education.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CoursePublishVO implements Serializable {
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
}
