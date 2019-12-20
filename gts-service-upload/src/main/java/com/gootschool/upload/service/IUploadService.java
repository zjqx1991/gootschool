package com.gootschool.upload.service;

import com.gootschool.common.response.RevanResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUploadService {

    /**
     * 上传文件
     * @param file
     * @return
     */
    RevanResponse upload(MultipartFile file, String hostName);

    /**
     * 上传视频文件
     * @param file
     * @return
     */
    RevanResponse uploadVideo(MultipartFile file);

    /**
     * 通过视频id删除阿里云视频
     * @param videoIds
     * @return
     */
    RevanResponse deleteVideoByVideoIds(List<String> videoIds);

    /**
     * 通过视频id删除阿里云
     * @param videoId
     * @return
     */
    RevanResponse deleteVideoById(String videoId);

    /**
     * 通过视频id获取视频信息
     * @param videoId
     * @return
     */
    RevanResponse fetchVideoInfoById(String videoId);
}
