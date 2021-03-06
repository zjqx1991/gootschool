package com.gootschool.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gootschool.common.code.RevanCodeEnum;
import com.gootschool.common.handler.RevanException;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.education.mapper.ITeacherMapper;
import com.gootschool.education.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gootschool.pojo.education.Teacher;
import com.gootschool.pojo.education.request.TeacherQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public RevanResponse teacherList() {
        List<Teacher> teacherList = this.baseMapper.selectList(null);
        for (Teacher teacher : teacherList) {
            System.out.println(teacher);
        }
        return RevanResponse.ok().data("items", teacherList);
    }

    @Override
    public RevanResponse listPage(Integer page, Integer size, TeacherQuery teacherQuery) {

        // 查询条件
        QueryWrapper<Teacher> queryWrapper = null;

        // 查询条件
        if (teacherQuery != null) {

            queryWrapper =  new QueryWrapper<>();
            queryWrapper.orderByAsc("sort");

            String name = teacherQuery.getName();
            Integer level = teacherQuery.getLevel();
            String begin = teacherQuery.getBegin();
            String end = teacherQuery.getEnd();

            if (StringUtils.isNotBlank(name)) {
                queryWrapper.likeRight("name", name);
            }

            if (level != null) {
                queryWrapper.eq("level", level);
            }

            if (StringUtils.isNotBlank(begin)) {
                queryWrapper.ge("gmt_create", begin);
            }

            if (StringUtils.isNotBlank(end)) {
                queryWrapper.le("gmt_create", end);
            }

        }

        // 创建分页查询对象
        Page<Teacher> pageParam = new Page<>(page, size);
        IPage<Teacher> tPage = this.baseMapper.selectPage(pageParam, queryWrapper);
        List<Teacher> datas = tPage.getRecords();
        long total = tPage.getTotal();
        return RevanResponse.ok().data("total", total).data("items", datas);
    }

    @Override
    public RevanResponse removeByTId(String id) {
        int delete = this.baseMapper.deleteById(id);
        if (delete == 0) {
            throw new RevanException(RevanCodeEnum.PARAM_FAIL);
        }
        return RevanResponse.ok();
    }

    @Override
    public RevanResponse saveOrUpdateTeacher(Teacher teacher) {
        // 1.新增
        if (StringUtils.isBlank(teacher.getId())) {
            int insert = this.baseMapper.insert(teacher);
            if (insert == 0) {
                throw new RevanException(RevanCodeEnum.PARAM_FAIL);
            }
            return RevanResponse.ok();
        }

        // 2.更新
        int update = this.baseMapper.updateById(teacher);
        if (update == 0) {
            throw new RevanException(RevanCodeEnum.PARAM_FAIL);
        }
        return RevanResponse.ok();
    }

    @Override
    public RevanResponse queryById(String id) {
        Teacher teacher = this.baseMapper.selectById(id);
        if (teacher == null) {
            throw new RevanException(RevanCodeEnum.PARAM_FAIL);
        }
        return RevanResponse.ok().data("teacher", teacher);
    }

    @Override
    public RevanResponse login() {
        return RevanResponse.ok();
    }

    @Override
    public RevanResponse userinfo() {
        return RevanResponse.ok().data("roles", "admin");
    }


}
