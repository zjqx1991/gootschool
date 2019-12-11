package com.gootschool.education.controller;


import com.gootschool.api.education.IEducationTeacherAPI;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.education.service.ITeacherService;
import com.gootschool.pojo.education.Teacher;
import com.gootschool.pojo.education.request.TeacherQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public RevanResponse list() {
        return this.teacherService.teacherList();
    }


    @Override
    public RevanResponse listPage(@PathVariable("page") Integer page,
                                  @PathVariable("size") Integer size,
                                  @RequestBody TeacherQuery teacherQuery) {
        return this.teacherService.listPage(page, size, teacherQuery);
    }

    @Override
    public RevanResponse removeById(@PathVariable("id") String id) {
        return this.teacherService.removeByTId(id);
    }

    @Override
    public RevanResponse saveOrUpdateTeacher(@RequestBody() Teacher teacher) {
        return this.teacherService.saveOrUpdateTeacher(teacher);
    }

    @Override
    public RevanResponse queryById(@PathVariable("id") String id) {
        return this.teacherService.queryById(id);
    }

    @Override
    public RevanResponse login() {
        return this.teacherService.login();
    }

    @Override
    public RevanResponse userinfo() {
        return this.teacherService.userinfo();
    }
}

