package com.gootschool.education.client;

import com.gootschool.api.upload.IUploadAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("gts-service-upload")
public interface IVideoClient extends IUploadAPI {
}
