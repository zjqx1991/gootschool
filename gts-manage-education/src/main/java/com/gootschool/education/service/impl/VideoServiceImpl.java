package com.gootschool.education.service.impl;

import com.gootschool.education.mapper.IVideoMapper;
import com.gootschool.education.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gootschool.pojo.education.Video;
import org.springframework.stereotype.Service;

/**
 * 课程视频 服务实现类
 *
 * @author Revan Wang
 * @since 2019-12-15
 */
@Service
public class VideoServiceImpl extends ServiceImpl<IVideoMapper, Video> implements IVideoService {

}
