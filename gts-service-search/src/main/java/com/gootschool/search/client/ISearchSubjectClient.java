package com.gootschool.search.client;

import com.gootschool.api.education.IEducationSubjectAPI;
import com.gootschool.api.education.IEducationTeacherAPI;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = "gts-manage-education")
public interface ISearchSubjectClient extends IEducationSubjectAPI {
}
