package com.gootschool.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gootschool.common.code.RevanCodeEnum;
import com.gootschool.common.handler.RevanException;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.education.mapper.IChapterMapper;
import com.gootschool.education.service.IChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gootschool.education.service.IVideoService;
import com.gootschool.pojo.education.Chapter;
import com.gootschool.pojo.education.dto.ChapterVO;
import com.gootschool.pojo.education.Video;
import com.gootschool.pojo.education.dto.VideoVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程 服务实现类
 *
 * @author Revan Wang
 * @since 2019-12-15
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<IChapterMapper, Chapter> implements IChapterService {

    @Autowired
    private IVideoService videoService;

    @Override
    public RevanResponse fetchChapterListByCourseId(String courseId) {
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        // 1.课程章节列表
        List<Chapter> chapterList = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(chapterList)) {
            // 没有内容
            return RevanResponse.ok();
        }

        // 2.课程小节列表
        QueryWrapper<Video> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id", courseId);
        List<Video> videoList = this.videoService.list(videoWrapper);

        // 3.遍历章节中包含小节
        List<ChapterVO> chapterVOList = new ArrayList<>();
        for (Chapter chapter : chapterList) {

            ChapterVO chapterVO = new ChapterVO();
            BeanUtils.copyProperties(chapter, chapterVO);
            chapterVOList.add(chapterVO);

            // 4.遍历小节
            for (Video video : videoList) {
                if (video.getChapterId().equals(chapter.getId())) {
                    VideoVO videoVO = new VideoVO();
                    BeanUtils.copyProperties(video, videoVO);
                    chapterVO.getChildren().add(videoVO);
                }
            }

        }

        return RevanResponse.ok().data("items", chapterVOList);
    }

    @Transactional
    @Override
    public RevanResponse saveOrUpdateChapter(Chapter chapter) {
        // 没有课程id就不保存章节
        if (StringUtils.isBlank(chapter.getCourseId())) {
            throw new RevanException(RevanCodeEnum.PARAM_FAIL);
        }

        // 保存
        if (StringUtils.isBlank(chapter.getId())) {
            int insert = baseMapper.insert(chapter);
            if (insert == 0) {
                throw new RevanException(RevanCodeEnum.CHAPTER_SAVE_FAIL);
            }
        }
        else {
            int update = baseMapper.updateById(chapter);
            if (update == 0) {
                throw new RevanException(RevanCodeEnum.CHAPTER_UPDATE_FAIL);
            }
        }

        return RevanResponse.ok().data("chapter", chapter);
    }

    @Transactional
    @Override
    public RevanResponse deleteChapterByChapterid(String chapterId) {

        // 1.删除章节
        int i = baseMapper.deleteById(chapterId);
        if (i == 0) {
            throw new RevanException(RevanCodeEnum.CHAPTER_REMOVE_FAIL);
        }

        // 2.删除章节中小节
        return this.videoService.deleteVideoByChapterId(chapterId);
    }
}
