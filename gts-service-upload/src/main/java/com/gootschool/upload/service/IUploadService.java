package com.gootschool.upload.service;

import com.gootschool.common.response.RevanResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {

    /**
     * 上传文件
     * @param file
     * @return
     */
    RevanResponse upload(MultipartFile file);
}
