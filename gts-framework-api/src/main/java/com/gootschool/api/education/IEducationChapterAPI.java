package com.gootschool.api.education;

import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.Chapter;
import com.gootschool.pojo.education.request.CourseInfoForm;
import com.gootschool.pojo.education.request.CourseQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api(value = "课程章节管理", description = "课程章节管理，提供增、删、改、查")
@RequestMapping("/education/chapter")
public interface IEducationChapterAPI {


    @ApiOperation("获取课程章节列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程id", required = true, paramType = "path", dataType = "string"),
    })
    @GetMapping("/list/{courseId}")
    RevanResponse fetchChapterListByCourseId(@PathVariable("courseId") String courseId);


    @ApiOperation("保存或者更新课程章节")
    @PostMapping("/saveOrUpdate")
    RevanResponse saveOrUpdateChapter(@RequestBody Chapter chapter);



}
