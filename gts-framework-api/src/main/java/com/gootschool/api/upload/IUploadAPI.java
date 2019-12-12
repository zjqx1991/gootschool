package com.gootschool.api.upload;

import com.gootschool.common.response.RevanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Api(value = "文件上传", description = "文件上传管理")
@RequestMapping("/upload")
public interface IUploadAPI {

    @ApiOperation("上传文件")
    @PostMapping("/image")
    RevanResponse upload(MultipartFile file);

}
