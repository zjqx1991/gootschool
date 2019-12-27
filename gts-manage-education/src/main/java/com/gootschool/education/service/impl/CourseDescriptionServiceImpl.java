package com.gootschool.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gootschool.common.code.RevanCodeEnum;
import com.gootschool.common.handler.RevanException;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.education.mapper.ICourseDescriptionMapper;
import com.gootschool.education.service.ICourseDescriptionService;
import com.gootschool.pojo.education.Course;
import com.gootschool.pojo.education.CourseDescription;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author Revan Wang
 * @since 2019-12-13
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<ICourseDescriptionMapper, CourseDescription> implements ICourseDescriptionService {

    @Override
    public RevanResponse getCourseDescriptionByCourseId(String courseId) {
        QueryWrapper<CourseDescription> wrapper = new QueryWrapper();
        wrapper.eq("id", courseId);
        CourseDescription courseDescription = baseMapper.selectOne(wrapper);
        if (courseDescription == null) {
            throw new RevanException(RevanCodeEnum.PARAM_FAIL);
        }
        return RevanResponse.ok().data("description", courseDescription);
    }
}
