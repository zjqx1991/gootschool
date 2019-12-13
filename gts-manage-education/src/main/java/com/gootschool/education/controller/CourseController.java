package com.gootschool.education.controller;



import com.gootschool.api.education.IEducationCourseAPI;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.education.service.ICourseService;
import com.gootschool.pojo.education.request.CourseInfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程 前端控制器
 *
 * @author Revan Wang
 * @since 2019-12-13
 */
@RestController
public class CourseController implements IEducationCourseAPI {

    @Autowired
    private ICourseService courseService;

    @Override
    public RevanResponse saveOrUpdateCourse(@RequestBody CourseInfoForm courseInfoForm) {
        return this.courseService.saveOrUpdateCourse(courseInfoForm);
    }
}

