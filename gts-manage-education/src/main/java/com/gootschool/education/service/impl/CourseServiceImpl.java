package com.gootschool.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gootschool.common.code.RevanCodeEnum;
import com.gootschool.common.constant.CourseConstant;
import com.gootschool.common.handler.RevanException;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.education.mapper.ICourseDescriptionMapper;
import com.gootschool.education.mapper.ICourseMapper;
import com.gootschool.education.service.ICourseService;
import com.gootschool.pojo.education.Course;
import com.gootschool.pojo.education.CourseDescription;
import com.gootschool.pojo.education.Subject;
import com.gootschool.pojo.education.request.CourseInfoForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 课程 服务实现类
 *
 * @author Revan Wang
 * @since 2019-12-13
 */
@Service
public class CourseServiceImpl extends ServiceImpl<ICourseMapper, Course> implements ICourseService {

    @Autowired
    private ICourseDescriptionMapper courseDescriptionMapper;

    @Transactional
    @Override
    public RevanResponse saveOrUpdateCourse(CourseInfoForm courseInfoForm) {

        // 保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);

        if (course.getId() == null) { // 保存课程
            List<Course> courseList = existCourse(courseInfoForm);
            // 课程已存在
//            if (CollectionUtils.isEmpty(courseList)) {
//            }

            course.setStatus(CourseConstant.COURSE_DRAFT);
            // 保存课程
            int insert = baseMapper.insert(course);
            if (insert == 0) {
                throw new RevanException(RevanCodeEnum.COURSE_SAVE_FAIL);
            }

            // 保存详情
            CourseDescription courseDes = new CourseDescription();
            courseDes.setId(course.getId());
            courseDes.setDescription(courseInfoForm.getDescription());
            int des = this.courseDescriptionMapper.insert(courseDes);
            if (des == 0) {
                throw new RevanException(RevanCodeEnum.COURSE_SAVE_FAIL);
            }
        } else { // 更新课程
            int courseState = this.baseMapper.updateById(course);
            if (courseState == 0) {
                throw new RevanException(RevanCodeEnum.COURSE_UPDATE_FAIL);
            }
            CourseDescription courseDes = new CourseDescription();
            courseDes.setId(course.getId());
            courseDes.setDescription(courseInfoForm.getDescription());
            int desState = this.courseDescriptionMapper.updateById(courseDes);
            if (desState == 0) {
                throw new RevanException(RevanCodeEnum.COURSE_UPDATE_FAIL);
            }
        }
        return RevanResponse.ok().data("course", course);
    }

    /**
     * 判断是否已存储
     *
     * @param courseInfoForm
     * @return
     */
    private List<Course> existCourse(CourseInfoForm courseInfoForm) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", courseInfoForm.getTeacherId())
                .eq("subject_id", courseInfoForm.getSubjectId())
                .eq("title", courseInfoForm.getTitle());
        List<Course> courses = this.baseMapper.selectList(queryWrapper);
        return courses;
    }

}
