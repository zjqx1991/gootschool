package com.gootschool.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.Video;

/**
 * 课程视频 服务类
 *
 * @author Revan Wang
 * @since 2019-12-15
 */
public interface IVideoService extends IService<Video> {

    /**
     * 通过章节id删除 - 小节
     * @param chapterId
     * @return
     */
    RevanResponse deleteVideoByChapterId(String chapterId);

    /**
     * 保存或更新小节
     * @param video
     * @return
     */
    RevanResponse saveOrUpdateVideo(Video video);

    /**
     * 通过小节节id删除
     * @param videoId
     * @return
     */
    RevanResponse deleteVideoByVideoid(String videoId);
}
