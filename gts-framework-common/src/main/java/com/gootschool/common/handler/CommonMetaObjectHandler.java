package com.gootschool.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class CommonMetaObjectHandler {}
//        implements MetaObjectHandler {
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        // 创建时间
//        this.setFieldValByName("gmtCreate", new Date(), metaObject);
//        // 更新时间
//        this.setFieldValByName("gmtModified", new Date(), metaObject);
//
//    }
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        // 更新时间
//        this.setFieldValByName("gmtModified", new Date(), metaObject);
//
//    }
//}
