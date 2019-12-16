package com.gootschool.education.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.Chapter;

/**
 * 课程 服务类
 *
 * @author Revan Wang
 * @since 2019-12-15
 */
public interface IChapterService extends IService<Chapter> {

    /**
     * 通过课程id获取章节
     * @param courseId
     * @return
     */
    RevanResponse fetchChapterListByCourseId(String courseId);

    /**
     * 保存或更新章节
     * @param chapter
     * @return
     */
    RevanResponse saveOrUpdateChapter(Chapter chapter);

    /**
     * 删除章节
     * @param chapterId
     * @return
     */
    RevanResponse deleteChapterByChapterid(String chapterId);
}
