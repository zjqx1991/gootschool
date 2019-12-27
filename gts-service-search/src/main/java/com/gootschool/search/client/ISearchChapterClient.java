package com.gootschool.search.client;

import com.gootschool.api.education.IEducationChapterAPI;
import com.gootschool.api.education.IEducationCourseAPI;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = "gts-manage-education")
public interface ISearchChapterClient extends IEducationChapterAPI {
}
