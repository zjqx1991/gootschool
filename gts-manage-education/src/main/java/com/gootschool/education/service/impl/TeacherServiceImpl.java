package com.gootschool.education.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gootschool.education.mapper.ITeacherMapper;
import com.gootschool.education.service.ITeacherService;
import com.gootschool.pojo.education.Teacher;
import org.springframework.stereotype.Service;

/**
 * 讲师 服务实现类
 *
 * @author Revan Wang
 * @since 2019-12-09
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<ITeacherMapper, Teacher> implements ITeacherService {

}
