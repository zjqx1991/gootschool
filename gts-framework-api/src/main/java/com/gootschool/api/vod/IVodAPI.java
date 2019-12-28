package com.gootschool.api.vod;

import com.gootschool.common.response.RevanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "视频点播管理", description = "视频点播管理")
@RequestMapping("/vod/video")
public interface IVodAPI {

    @ApiOperation("删除视频文件")
    @PostMapping("/deleteIds")
    RevanResponse deleteVideoByVideoIds(@RequestParam("videoIds") List<String> videoIds);


    @ApiOperation("删除视频文件")
    @PostMapping("/delete/{videoId}")
    RevanResponse deleteVideoById(@PathVariable("videoId") String videoId);


    @ApiOperation("视频信息")
    @GetMapping("/videoInfo/{videoId}")
    RevanResponse fetchVideoInfoById(@PathVariable("videoId") String videoId);

    @ApiOperation("视频播放凭证")
    @GetMapping("/playauth/{videoId}")
    RevanResponse fetchVideoPlayAuth(@PathVariable("videoId") String videoId);

}
