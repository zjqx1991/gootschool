package com.gootschool.upload.controller;

import com.gootschool.api.upload.IUploadAPI;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.upload.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class GTSUploadController implements IUploadAPI {

    @Autowired
    private IUploadService uploadService;

    @Override
    public RevanResponse upload(MultipartFile file) {
        return this.uploadService.upload(file);
    }

}
