package com.gootschool.education.controller;



import com.gootschool.api.education.IEducationSubjectAPI;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.education.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 课程科目 前端控制器
 *
 * @author Revan Wang
 * @since 2019-12-12
 */
@RestController
public class SubjectController implements IEducationSubjectAPI {

    @Autowired
    private ISubjectService subjectService;

    @Override
    public RevanResponse importExcel(MultipartFile file) {
        return this.subjectService.importExcel(file);
    }

    @Override
    public RevanResponse nestedList() {
        return this.subjectService.nestedList();
    }
}

