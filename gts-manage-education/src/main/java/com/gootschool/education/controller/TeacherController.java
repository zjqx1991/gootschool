package com.gootschool.education.controller;


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
@RequestMapping("/education/teacher")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @GetMapping("/list")
    public List<Teacher> list() {
        List<Teacher> list = this.teacherService.list(null);

        for (Teacher teacher : list) {
            System.out.println(teacher);
        }
        return list;
    }


    @DeleteMapping("/delete/{id}")
    public boolean removeById(@PathVariable String id){
        return teacherService.removeById(id);
    }

}

