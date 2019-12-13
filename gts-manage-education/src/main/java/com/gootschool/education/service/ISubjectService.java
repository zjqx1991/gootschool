package com.gootschool.education.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.Subject;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Revan Wang
 * @since 2019-12-12
 */
public interface ISubjectService extends IService<Subject> {

    /**
     * 通过 Excel 导入科目
     * @param file
     * @return
     */
    RevanResponse importExcel(MultipartFile file);

    /**
     * 获取课程分类列表
     */
    RevanResponse nestedList();

    /**
     * 删除课程
     * @param id 课程id
     * @return
     */
    RevanResponse deleteSubjectById(String id);
}
