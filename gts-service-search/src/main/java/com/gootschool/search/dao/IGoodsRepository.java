package com.gootschool.search.dao;

import com.gootschool.pojo.search.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IGoodsRepository extends ElasticsearchRepository<Goods, String> {
}
