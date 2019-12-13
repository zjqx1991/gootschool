package com.gootschool.api.education;

import com.gootschool.common.response.RevanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Api(value = "科目管理", description = "科目通过Excel导入")
@RequestMapping("/education/subject")
public interface IEducationSubjectAPI {

    @ApiOperation("通过Excel导入科目")
    @PostMapping("/import")
    RevanResponse importExcel(MultipartFile file);

    @ApiOperation("嵌套数据列表")
    @GetMapping("/list")
    RevanResponse nestedList();

}
