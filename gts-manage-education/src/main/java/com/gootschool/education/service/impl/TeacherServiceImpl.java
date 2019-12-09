package com.gootschool.education.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.education.mapper.ITeacherMapper;
import com.gootschool.education.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gootschool.pojo.education.Teacher;
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
    public RevanResponse listPage(Integer page, Integer size) {
        // 创建分页查询对象
        Page<Teacher> pageParam = new Page<>(page, size);
        IPage<Teacher> tPage = this.baseMapper.selectPage(pageParam, null);
        List<Teacher> datas = tPage.getRecords();
        long total = tPage.getTotal();
        return RevanResponse.ok().data("total", total).data("items", datas);
    }

    @Override
    public RevanResponse removeByTId(String id) {
        int delete = this.baseMapper.deleteById(id);
        if (delete != 1) {
            throw new RuntimeException(id + "参数错误");
        }
        return RevanResponse.ok();
    }

}
