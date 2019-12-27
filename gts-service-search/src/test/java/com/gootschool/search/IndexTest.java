package com.gootschool.search;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.BeanProperty;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.Course;
import com.gootschool.pojo.education.request.CourseQuery;
import com.gootschool.pojo.search.Goods;
import com.gootschool.search.client.ISearchCourseClient;
import com.gootschool.search.client.ISearchSubjectClient;
import com.gootschool.search.dao.IGoodsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GTSSearchApplication.class)
public class IndexTest {

    @Autowired
    private ElasticsearchTemplate esTemplate;
    @Autowired
    private ISearchCourseClient searchCourseClient;
    @Autowired
    private IGoodsRepository goodsRepository;
    @Autowired
    private ISearchSubjectClient subjectClient;

    @Test
    public void createIndex() {
        this.esTemplate.createIndex(Goods.class);
        this.esTemplate.putMapping(Goods.class);
    }

    @Test
    public void loadData() {

        int page = 1;
        int rows = 10;
        int size = 0;

//        do {
        System.out.println("数据请求：" + this.searchCourseClient);
        RevanResponse response = this.searchCourseClient.courseList(page, rows, new CourseQuery());

        List items = (List) response.getData().get("items");

        List<Goods> goodsList = new ArrayList<>();

        for (Object item : items) {
            Course course = JSON.parseObject(JSON.toJSONString(item), Course.class);
            Goods goods = new Goods();
            BeanUtils.copyProperties(course, goods);
            goodsList.add(goods);
        }

        this.goodsRepository.saveAll(goodsList);
//        }while (size == 100);
    }

    @Test
    public void subjectTest() {
        RevanResponse subjectResponse = this.subjectClient.querySubjectsByIds(Arrays.asList("1205319903793246209", "1205319903965212674"));
        System.out.println(subjectResponse);
    }

}
