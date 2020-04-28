package com.sxgokit.bas.config.mybatisplus;

import java.util.Date;
import com.sxgokit.bas.base.DataPool;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName AutoMetaObjectHandler.java
 * @Description：MybatisPlus公共字段自动填充
 * @createTime 2019年11月04日 11:18:00
 */
@Component
public class AutoMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("id", DataPool.getSnowFlakeId(), metaObject);
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("delFlag", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
