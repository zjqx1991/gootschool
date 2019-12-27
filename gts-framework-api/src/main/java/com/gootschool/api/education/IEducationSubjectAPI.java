package com.gootschool.api.education;

import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.Subject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(value = "课程科目管理", description = "课程科目管理，提供增、删、改、查")
@RequestMapping("/education/subject")
public interface IEducationSubjectAPI {

    @ApiOperation("通过Excel导入科目")
    @PostMapping("/import")
    RevanResponse importExcel(MultipartFile file);

    @ApiOperation("嵌套数据列表")
    @GetMapping("/list")
    RevanResponse nestedList();

    @ApiOperation("保存课程")
    @PostMapping("/save")
    RevanResponse saveSubject(@RequestBody Subject subject);


    @ApiOperation("删除课程")
    @DeleteMapping("/delete/{id}")
    RevanResponse deleteSubjectById(@PathVariable("id") String id);

    @ApiOperation("通过id获取对应课程名称")
    @GetMapping("/ids")
    RevanResponse querySubjectsByIds(@RequestParam("ids") List<String> ids);

}
