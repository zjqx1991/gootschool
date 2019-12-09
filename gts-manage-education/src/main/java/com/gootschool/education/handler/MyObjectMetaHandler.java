package com.gootschool.education.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyObjectMetaHandler
        implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        // 更新时间
        this.setFieldValByName("gmtModified",new Date(),metaObject);
        // 版本号
        this.setFieldValByName("version", 1, metaObject);
        // 逻辑删除
        this.setFieldValByName("deleted", false, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
