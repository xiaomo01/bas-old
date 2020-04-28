package com.sxgokit.bas;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName NoWarnMapper.java
 * @createTime 2019年10月26日 01:31:00
 * 取消启动项目时WARN报警 No MyBatis mapper was found in '[com.sxgokit.bas]' package. Please check your configuration
 */
@Mapper
public interface NoWarnMapper {
    //解决思路:既然扫描主包下没有mapper,建立一个空的mapper接口就不会报错了
}
