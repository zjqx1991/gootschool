package com.gootschool.education.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gootschool.education.mapper.ICourseDescriptionMapper;
import com.gootschool.education.service.ICourseDescriptionService;
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

}
