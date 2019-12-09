package com.gootschool.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.Teacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Revan Wang
 * @since 2019-12-09
 */
public interface ITeacherService extends IService<Teacher> {

    /**
     * 获取讲师列表
     * @return
     */
    RevanResponse teacherList();


    /**
     * 获取讲师列表
     * @param page 页码
     * @param size 个数
     * @return
     */
    RevanResponse listPage(Integer page, Integer size);

    /**
     * 删除讲师
     * @param id 讲师id
     * @return
     */
    RevanResponse removeByTId(String id);

}
