package com.gootschool.search.web.controller;

import com.gootschool.api.search.ISearchCourseAPI;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.search.request.SearchRequest;
import com.gootschool.search.service.ISearchGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SearchCourseController implements ISearchCourseAPI {

    @Autowired
    private ISearchGoodsService goodsService;

    @Override
    public RevanResponse courseList(@RequestBody SearchRequest searchRequest) {
        return this.goodsService.queryCourseList(searchRequest);
    }
}
