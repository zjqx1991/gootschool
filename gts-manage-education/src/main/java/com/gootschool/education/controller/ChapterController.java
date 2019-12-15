package com.gootschool.education.controller;


import com.gootschool.api.education.IEducationChapterAPI;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.education.service.IChapterService;
import com.gootschool.pojo.education.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 课程章节 前端控制器
 *
 * @author Revan Wang
 * @since 2019-12-15
 */
@RestController
public class ChapterController implements IEducationChapterAPI {

    @Autowired
    private IChapterService chapterService;

    @Override
    public RevanResponse fetchChapterListByCourseId(@PathVariable("courseId") String courseId) {
        return chapterService.fetchChapterListByCourseId(courseId);
    }

    @Override
    public RevanResponse saveOrUpdateChapter(@RequestBody Chapter chapter) {
        return chapterService.saveOrUpdateChapter(chapter);
    }
}

