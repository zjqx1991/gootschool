package com.gootschool.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gootschool.pojo.usercenter.Usercenter;
import com.gootschool.usercenter.mapper.IUsercenterMapper;
import com.gootschool.usercenter.service.IUsercenterService;
import org.springframework.stereotype.Service;

/**
 * 会员表 服务实现类
 *
 * @author Revan Wang
 * @since 2019-12-19
 */
@Service
public class UsercenterServiceImpl extends ServiceImpl<IUsercenterMapper, Usercenter> implements IUsercenterService {

}
