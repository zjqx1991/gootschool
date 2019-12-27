package com.gootschool.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.Course;
import com.gootschool.pojo.education.Subject;
import com.gootschool.pojo.education.Teacher;
import com.gootschool.pojo.search.Goods;
import com.gootschool.pojo.search.request.SearchRequest;
import com.gootschool.search.client.ISearchCourseClient;
import com.gootschool.search.client.ISearchSubjectClient;
import com.gootschool.search.client.ISearchTeacherClient;
import com.gootschool.search.dao.IGoodsRepository;
import com.gootschool.search.service.ISearchGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class SearchGoodsServiceImpl implements ISearchGoodsService {

    @Autowired
    private IGoodsRepository goodsRepository;
    @Autowired
    private ISearchCourseClient searchCourseClient;
    @Autowired
    private ISearchTeacherClient teacherClient;
    @Autowired
    private ISearchSubjectClient subjectClient;


    @Override
    public void publishGoodsByCourseId(String courseId) {
        // 1.获取课程
        RevanResponse courseInfo = this.searchCourseClient.getCourseInfoFormById(courseId);
        Object data = courseInfo.getData().get("courseInfo");
        Course course = JSON.parseObject(JSON.toJSONString(data), Course.class);

        // 2.查询讲师信息
        RevanResponse teacherResponse = this.teacherClient.queryById(course.getTeacherId());
        Object teacherData = teacherResponse.getData().get("teacher");
        Teacher teacher = JSON.parseObject(JSON.toJSONString(teacherData), Teacher.class);

        // 3.分类名称
        RevanResponse subjectResponse = this.subjectClient.querySubjectsByIds(Arrays.asList(course.getSubjectId1(), course.getSubjectId2()));
        Object subjectsData = subjectResponse.getData().get("subjects");
        List<Subject> subjects = JSON.parseArray(JSON.toJSONString(subjectsData), Subject.class);

        // 拼接商品
        Goods goods = new Goods();
        BeanUtils.copyProperties(course, goods);
        //讲师名称
        goods.setTeacherName(teacher.getName());
        //科目
        goods.setSubjectName1(subjects.get(0).getTitle());
        goods.setSubjectName2(subjects.get(1).getTitle());
        //搜索条件
        goods.setSearchWord(
                teacher.getName() + " " +
                        subjects.get(0).getTitle() + " " +
                        subjects.get(1).getTitle()
        );

        // 保存到索引库
        this.goodsRepository.save(goods);
    }

    @Override
    public RevanResponse queryCourseList(SearchRequest request) {

        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();


        // 查询条件
        String key = request.getKey();
        // TODO 禁止没有条件查询，（因为数据太多）
        if (StringUtils.isBlank(key)) {
            int page = request.getPage();
            int size = request.getSize();
            queryBuilder.withPageable(PageRequest.of(page - 1, size));

            Page<Goods> search = this.goodsRepository.search(queryBuilder.build());

            return RevanResponse.ok().data("items", search.getContent())
                    .data("totalPage", search.getTotalPages());
        }

        // 1.对可以进行全文检索查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("searchWord", key).operator(Operator.AND));
        // 2、通过sourceFilter设置返回的结果字段,我们只需要id、skus、subTitle
        // 来选择要返回的结果，否则返回一堆没有的数据，影响查询效率
        //queryBuilder.withSourceFilter(new FetchSourceFilter(
        //       new String[]{"id","skus","subTitle"}, null));

        // 3.分页
        int page = request.getPage();
        int size = request.getSize();
        queryBuilder.withPageable(PageRequest.of(page - 1, size));

        Page<Goods> search = this.goodsRepository.search(queryBuilder.build());

        return RevanResponse.ok().data("items", search.getContent())
                .data("totalPage", search.getTotalPages());
    }

}
