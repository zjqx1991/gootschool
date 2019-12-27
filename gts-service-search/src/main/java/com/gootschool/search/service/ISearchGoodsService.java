package com.gootschool.search.service;

import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.search.request.SearchRequest;

public interface ISearchGoodsService {

    /**
     * 发布课程，把课程添加到索引库
     * @param courseId 课程id
     */
    void publishGoodsByCourseId(String courseId);


    /**
     * 获取课程列表
     * @param searchRequest
     * @return
     */
    RevanResponse queryCourseList(SearchRequest searchRequest);
}
