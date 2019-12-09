package com.gootschool.education.controller;


import com.gootschool.api.education.IEducationTeacherAPI;
import com.gootschool.education.service.ITeacherService;
import com.gootschool.pojo.education.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Revan Wang
 * @since 2019-12-09
 */
@RestController
public class TeacherController implements IEducationTeacherAPI {

    @Autowired
    private ITeacherService teacherService;


    @Override
    public List list() {
        List<Teacher> list = this.teacherService.list(null);
        for (Teacher teacher : list) {
            System.out.println(teacher);
        }
        return list;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }
}

