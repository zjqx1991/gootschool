package com.gootschool.vod.web.controller;

import com.gootschool.api.vod.IVodAPI;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.vod.service.IVodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GTSVodController implements IVodAPI {

    @Autowired
    private IVodService vodService;

    @Override
    public RevanResponse deleteVideoByVideoIds(@RequestParam("videoIds") List<String> videoIds) {
        return this.vodService.deleteVideoByVideoIds(videoIds);
    }

    @Override
    public RevanResponse deleteVideoById(@PathVariable("videoId") String videoId) {
        return this.vodService.deleteVideoById(videoId);
    }

    @Override
    public RevanResponse fetchVideoInfoById(@PathVariable("videoId") String videoId) {
        return this.vodService.fetchVideoInfoById(videoId);
    }

    @Override
    public RevanResponse fetchVideoPlayAuth(@PathVariable("videoId") String videoId) {
        return this.vodService.fetchVideoPlayAuth(videoId);
    }
}
