package com.gootschool.education.controller;



import com.gootschool.api.education.IEducationCourseDescriptionAPI;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.education.service.ICourseDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程 前端控制器
 *
 * @author Revan Wang
 * @since 2019-12-13
 */
@RestController
public class CourseDescriptionController implements IEducationCourseDescriptionAPI {

    @Autowired
    private ICourseDescriptionService descriptionService;

    @Override
    public RevanResponse getCourseDescriptionByCourseId(@PathVariable("courseId") String courseId) {
        return this.descriptionService.getCourseDescriptionByCourseId(courseId);
    }
}

