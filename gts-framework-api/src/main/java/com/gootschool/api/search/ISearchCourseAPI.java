package com.gootschool.api.search;

import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.search.request.SearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api(value = "搜索课程管理", description = "搜索课程管理，提供增、删、改、查")
@RequestMapping("/search/course")
public interface ISearchCourseAPI {


    @ApiOperation("搜索课程")
    @PostMapping("/list")
    RevanResponse courseList(@RequestBody SearchRequest searchRequest);

    @ApiOperation("课程详情")
    @GetMapping("/info/{courseId}")
    RevanResponse courseInfo(@PathVariable("courseId") String courseId);

}
