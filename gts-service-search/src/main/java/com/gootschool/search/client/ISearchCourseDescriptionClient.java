package com.gootschool.search.client;

import com.gootschool.api.education.IEducationCourseDescriptionAPI;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = "gts-manage-education")
public interface ISearchCourseDescriptionClient extends IEducationCourseDescriptionAPI {
}
