package com.gootschool.api.education;

import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.Chapter;
import com.gootschool.pojo.education.Video;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api(value = "课程小节管理", description = "课程小节管理，提供增、删、改、查")
@RequestMapping("/education/video")
public interface IEducationVideoAPI {



    @ApiOperation("保存或者更新课程小节")
    @PostMapping("/saveOrUpdate")
    RevanResponse saveOrUpdateVideo(@RequestBody Video video);


    @ApiOperation("根据小节id删除章节")
    @PostMapping("/delete/{videoId}")
    RevanResponse deleteVideoByVideoid(@PathVariable("videoId") String videoId);



}
