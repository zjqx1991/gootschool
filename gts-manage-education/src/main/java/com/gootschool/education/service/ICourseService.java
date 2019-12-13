package com.gootschool.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.Course;
import com.gootschool.pojo.education.request.CourseInfoForm;

/**
 * 课程 服务类
 *
 * @author Revan Wang
 * @since 2019-12-13
 */
public interface ICourseService extends IService<Course> {

    /**
     * 保存或者更新课程
     * @param courseInfoForm
     * @return
     */
    RevanResponse saveOrUpdateCourse(CourseInfoForm courseInfoForm);
}
