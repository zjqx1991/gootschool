package com.gootschool.api.upload;

import com.gootschool.common.response.RevanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Api(value = "文件上传", description = "文件上传管理")
@RequestMapping("/upload")
public interface IUploadAPI {

    @ApiOperation("上传文件")
    @PostMapping("/image")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "hostName", value = "存储目录")
    )
    RevanResponse upload(MultipartFile file, @RequestParam(value = "hostName", required = false) String hostName);


    @ApiOperation("上传视频文件")
    @PostMapping("/video")
    RevanResponse uploadVideo(MultipartFile file);

}
