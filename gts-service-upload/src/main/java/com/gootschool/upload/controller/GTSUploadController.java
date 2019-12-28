package com.gootschool.upload.controller;

import com.gootschool.api.upload.IUploadAPI;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.upload.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class GTSUploadController implements IUploadAPI {

    @Autowired
    private IUploadService uploadService;

    @Override
    public RevanResponse upload(MultipartFile file, @RequestParam(value = "hostName", required = false) String hostName) {
        return this.uploadService.upload(file, hostName);
    }

    @Override
    public RevanResponse uploadVideo(MultipartFile file) {
        return this.uploadService.uploadVideo(file);
    }

}
