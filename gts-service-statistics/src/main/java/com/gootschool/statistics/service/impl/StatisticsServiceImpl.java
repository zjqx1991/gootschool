package com.gootschool.statistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gootschool.pojo.statistics.Statistics;
import com.gootschool.statistics.mapper.IStatisticsMapper;
import com.gootschool.statistics.service.IStatisticsService;
import org.springframework.stereotype.Service;

/**
 * 网站统计日数据 服务实现类
 *
 * @author Revan Wang
 * @since 2019-12-19
 */
@Service
public class StatisticsServiceImpl extends ServiceImpl<IStatisticsMapper, Statistics> implements IStatisticsService {

}
