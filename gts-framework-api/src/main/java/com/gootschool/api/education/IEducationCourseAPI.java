package com.gootschool.api.education;

import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.request.CourseInfoForm;
import com.gootschool.pojo.education.request.CourseQuery;
import com.gootschool.pojo.education.request.TeacherQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api(value = "课程管理", description = "课程管理，提供增、删、改、查")
@RequestMapping("/education/course")
public interface IEducationCourseAPI {


    @ApiOperation("获取课程列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "个数", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "courseQuery", value = "查询条件", required = false),
    })
    @PostMapping("/list/{page}/{size}")
    RevanResponse courseList(@PathVariable("page") Integer page,
                           @PathVariable("size") Integer size,
                           @RequestBody(required = false) CourseQuery courseQuery);

    @ApiOperation("获取课程信息")
    @GetMapping("/CourseInfoForm/{id}")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "课程id", paramType = "string", required = true)
    )
    RevanResponse getCourseInfoFormById(@PathVariable("id") String id);

    @ApiOperation("保存或更新课程")
    @PostMapping("/saveOrUpdate")
    RevanResponse saveOrUpdateCourse(@RequestBody CourseInfoForm courseInfoForm);



}
