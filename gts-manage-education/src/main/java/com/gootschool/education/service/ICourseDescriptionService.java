package com.gootschool.education.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.CourseDescription;

/**
 * 课程简介 服务类
 *
 * @author Revan Wang
 * @since 2019-12-13
 */
public interface ICourseDescriptionService extends IService<CourseDescription> {

    /**
     * 获取课程描述
     * @param courseId
     * @return
     */
    RevanResponse getCourseDescriptionByCourseId(String courseId);
}
