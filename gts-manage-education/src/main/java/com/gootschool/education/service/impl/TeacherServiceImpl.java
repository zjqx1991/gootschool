package com.gootschool.education.service.impl;

import com.gootschool.education.mapper.ITeacherMapper;
import com.gootschool.education.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gootschool.pojo.education.Teacher;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Revan Wang
 * @since 2019-12-09
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<ITeacherMapper, Teacher> implements ITeacherService {

}
