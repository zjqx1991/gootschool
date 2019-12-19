package com.gootschool.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gootschool.common.code.RevanCodeEnum;
import com.gootschool.common.handler.RevanException;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.education.client.IVideoClient;
import com.gootschool.education.mapper.IVideoMapper;
import com.gootschool.education.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gootschool.pojo.education.Video;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程视频 服务实现类
 *
 * @author Revan Wang
 * @since 2019-12-15
 */
@Service
public class VideoServiceImpl extends ServiceImpl<IVideoMapper, Video> implements IVideoService {

    @Autowired
    private IVideoClient videoClient;

    @Override
    public RevanResponse saveOrUpdateVideo(Video video) {
        String courseId = video.getCourseId();
        String chapterId = video.getChapterId();
        if (StringUtils.isBlank(courseId) || StringUtils.isBlank(chapterId)) {
            throw new RevanException(RevanCodeEnum.VIDEO_COURSE_CHAPTER_NOT_FOUND);
        }
        Video videoNew = new Video();
        BeanUtils.copyProperties(video, videoNew);

        if (StringUtils.isNotBlank(videoNew.getId())) {
            //更新
            int update = baseMapper.updateById(videoNew);
            if (update == 0) {
                throw new RevanException(RevanCodeEnum.VIDEO_UPDATE_FAIL);
            }
        }
        else {
            int insert = baseMapper.insert(videoNew);
            if (insert == 0) {
                throw new RevanException(RevanCodeEnum.VIDEO_SAVE_FAIL);
            }
        }
        return RevanResponse.ok().data("video", videoNew);
    }

    @Transactional
    @Override
    public RevanResponse deleteVideoByVideoid(String videoId) {

        List<String> videos = new ArrayList<>();
        videos.add(videoId);
        // 删除视频
        RevanResponse response = this.videoClient.deleteVideoByVideoIds(videos);

        int delete = baseMapper.deleteById(videoId);
        if (delete == 0) {
            throw new RevanException(RevanCodeEnum.VIDEO_REMOVE_FAIL);
        }
        return RevanResponse.ok();
    }

    @Transactional
    @Override
    public RevanResponse deleteVideoByChapterId(String chapterId) {

        // 删除条件
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);

        List<Video> videos = baseMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(videos)) {
            return RevanResponse.ok();
        }
        // 1.删除阿里云视频
        List<String> list = new ArrayList<>();
        for (Video video : videos) {
            if (StringUtils.isNotBlank(video.getVideoSourceId())) {
                list.add(video.getVideoSourceId());
            }
        }
        RevanResponse revanResponse = this.videoClient.deleteVideoByVideoIds(list);

        // 2.删除小节
        int delete = baseMapper.delete(wrapper);
        if (delete == 0) {
            throw new RevanException(RevanCodeEnum.VIDEO_REMOVE_FAIL);
        }
        return RevanResponse.ok();
    }

}
