package com.gootschool.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gootschool.common.code.RevanCodeEnum;
import com.gootschool.common.constants.CourseConstants;
import com.gootschool.common.constants.PriceConstants;
import com.gootschool.common.handler.RevanException;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.education.mapper.ICourseDescriptionMapper;
import com.gootschool.education.mapper.ICourseMapper;
import com.gootschool.education.service.ICourseService;
import com.gootschool.pojo.education.Course;
import com.gootschool.pojo.education.CourseDescription;
import com.gootschool.pojo.education.request.CourseInfoForm;
import com.gootschool.pojo.education.request.CourseQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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


    @Override
    public RevanResponse courseList(Integer page, Integer size, CourseQuery courseQuery) {

        // 1.没有查询条件
        QueryWrapper<Course> queryWrapper = null;

        // 查询条件
        if (courseQuery != null) {
            queryWrapper = new QueryWrapper<>();
            String title = courseQuery.getTitle();
            String teacherId = courseQuery.getTeacherId();
            String subjectId1 = courseQuery.getSubjectId1();
            String subjectId2 = courseQuery.getSubjectId2();

            if (StringUtils.isNotBlank(title)) {
                queryWrapper.like("title", title);
            }

            if (StringUtils.isNotBlank(teacherId)) {
                queryWrapper.eq("teacher_id", teacherId);
            }

            if (StringUtils.isNotBlank(subjectId1)) {
                queryWrapper.eq("subject_id1", subjectId1);
            }

            if (StringUtils.isNotBlank(subjectId2)) {
                queryWrapper.eq("subject_id2", subjectId2);
            }

        }

        // 2.查询
        Page<Course> courseP = new Page<>(page, size);
        IPage<Course> courseIPage = this.baseMapper.selectPage(courseP, queryWrapper);
        List<Course> records = courseIPage.getRecords();
        long total = courseIPage.getTotal();
        return RevanResponse.ok().data("total", total).data("items", records);
    }

    @Transactional
    @Override
    public RevanResponse saveOrUpdateCourse(CourseInfoForm courseInfoForm) {

        // 保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);

        if (StringUtils.isBlank(course.getId())) { // 保存课程
            List<Course> courseList = existCourse(courseInfoForm);
            // 课程已存在
//            if (CollectionUtils.isEmpty(courseList)) {
//            }

            course.setStatus(CourseConstants.COURSE_DRAFT);
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

    @Override
    public RevanResponse getCourseInfoFormById(String id) {
        Course course = baseMapper.selectById(id);
        if (course == null) {
            throw new RevanException(RevanCodeEnum.PARAM_FAIL);
        }
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course, courseInfoForm);
        // 设置显示精度：舍去多余的位数
        courseInfoForm.setPrice(courseInfoForm.getPrice()
                .setScale(PriceConstants.DISPLAY_SCALE, BigDecimal.ROUND_FLOOR));

        //查询课程描述
        CourseDescription courseDescription = this.courseDescriptionMapper.selectById(id);
        if (courseDescription != null) {
            courseInfoForm.setDescription(courseDescription.getDescription());
        }

        return RevanResponse.ok().data("courseInfo", courseInfoForm);
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
                .eq("subject_id1", courseInfoForm.getSubjectId1())
                .eq("subject_id2", courseInfoForm.getSubjectId2())
                .eq("title", courseInfoForm.getTitle());
        List<Course> courses = this.baseMapper.selectList(queryWrapper);
        return courses;
    }

}
