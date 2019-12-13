package com.gootschool.api.education;

import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.request.CourseInfoForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api(value = "课程管理", description = "课程管理，提供增、删、改、查")
@RequestMapping("/education/course")
public interface IEducationCourseAPI {


    @ApiOperation("保存或更新课程")
    @PostMapping("/saveOrUpdate")
    RevanResponse saveOrUpdateCourse(@RequestBody CourseInfoForm courseInfoForm);



}
