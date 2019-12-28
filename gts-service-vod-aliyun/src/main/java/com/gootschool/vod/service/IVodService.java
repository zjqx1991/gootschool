package com.gootschool.vod.service;

import com.gootschool.common.response.RevanResponse;

import java.util.List;

public interface IVodService {

    /**
     * 删除视频文件
     * @param videoIds
     * @return
     */
    RevanResponse deleteVideoByVideoIds(List<String> videoIds);

    /**
     * 删除视频文件
     * @param videoId
     * @return
     */
    RevanResponse deleteVideoById(String videoId);

    /**
     * 视频信息
     * @param videoId
     * @return
     */
    RevanResponse fetchVideoInfoById(String videoId);

    /**
     * 视频播放凭证
     * @param videoId
     * @return
     */
    RevanResponse fetchVideoPlayAuth(String videoId);
}
