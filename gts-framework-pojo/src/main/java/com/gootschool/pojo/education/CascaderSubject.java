package com.gootschool.pojo.education;

import com.gootschool.pojo.education.Cascader;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CascaderSubject {
    private String value;
    private String label;
    private List<Cascader> children = new ArrayList<>();
}
