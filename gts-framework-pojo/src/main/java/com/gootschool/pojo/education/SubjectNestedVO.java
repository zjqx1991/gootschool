package com.gootschool.pojo.education;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SubjectNestedVO {
    private String id;
    private String title;
    private List<SubjectNestedVO> children = new ArrayList<>();
}
