package com.gootschool.api.education;

import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.request.CourseInfoForm;
import com.gootschool.pojo.education.request.CourseQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api(value = "课程管理", description = "课程管理，提供增、删、改、查")
@RequestMapping("/education/courseDescription")
public interface IEducationCourseDescriptionAPI {


    @ApiOperation("获取课程描述-返回 CourseDescription")
    @GetMapping("/{courseId}")
    RevanResponse getCourseDescriptionByCourseId(@PathVariable("courseId") String courseId);

}
