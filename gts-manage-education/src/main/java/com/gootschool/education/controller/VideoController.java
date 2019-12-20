package com.gootschool.education.controller;


import com.gootschool.api.education.IEducationVideoAPI;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.education.service.IVideoService;
import com.gootschool.pojo.education.Video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程视频 前端控制器
 *
 * @author Revan Wang
 * @since 2019-12-15
 */
@RestController
public class VideoController implements IEducationVideoAPI {

    @Autowired
    private IVideoService videoService;

    @Override
    public RevanResponse saveOrUpdateVideo(@RequestBody Video video) {
        return videoService.saveOrUpdateVideo(video);
    }

    @Override
    public RevanResponse deleteVideoByVideoId(@PathVariable("videoId") String videoId) {
        return videoService.deleteVideoByVideoId(videoId);
    }
}

