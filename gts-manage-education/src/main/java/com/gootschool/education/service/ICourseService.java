package com.gootschool.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.Course;
import com.gootschool.pojo.education.dto.CoursePublishVO;
import com.gootschool.pojo.education.request.CourseInfoForm;
import com.gootschool.pojo.education.request.CourseQuery;

/**
 * 课程 服务类
 *
 * @author Revan Wang
 * @since 2019-12-13
 */
public interface ICourseService extends IService<Course> {

    /**
     * 获取课程列表信息
     * @param page 页码
     * @param size 每页个数
     * @param courseQuery 查询条件
     * @return
     */
    RevanResponse courseList(Integer page, Integer size, CourseQuery courseQuery);


    /**
     * 保存或者更新课程
     * @param courseInfoForm
     * @return
     */
    RevanResponse saveOrUpdateCourse(CourseInfoForm courseInfoForm);

    /**
     * 获取课程信息，返回CourseInfoForm
     * @param id
     * @return
     */
    RevanResponse getCourseInfoFormById(String id);


    /**
     * 发布课程
     * @param courseId
     * @return
     */
    RevanResponse publishCourse(String courseId);

    /**
     * 通过课程id获取发布时课程显示详情
     * @param courseId
     * @return
     */
    RevanResponse courseInfo(String courseId);

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    RevanResponse deleteCourse(String courseId);

    /**
     * 获取课程
     * @param courseId
     * @return
     */
    RevanResponse getCourseById(String courseId);
}
