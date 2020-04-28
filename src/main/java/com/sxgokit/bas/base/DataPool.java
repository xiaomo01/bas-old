package com.sxgokit.bas.base;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName DataPool.java
 * @Description：全局基础配置类
 * @createTime 2019年10月27日 15:24:00
 */

public class DataPool {

    /**
     * 字符编码格式 目前支持 utf-8
     */
    public final static String INPUT_CHARSET = "utf-8";

    /**
     * 盐值  用于密码加密处理
     */
    public static final String PASS_SALF = "mprj1pcvyo2byjc5xfx4s4yin5xkydk2";

    /**
     * 用户初始密码(123456 加盐值加密)
     */
    public static final String LOGIN_PASS = "dfc7e187984567f5475c8091a666515e";

    /**
     * boolean值 true 对应Integer编码（用于数据返回处理）
     */
    public static Integer FLG_TRUE_CODE = 1;

    /**
     * boolean值 false 对应Integer编码（用于数据返回处理）
     */
    public static Integer FLG_FALSE_CODE = 2;


    /**
     * 分布式部署前使用,使用为固定参数
     * Snowflake对应参数1为终端ID参数2为数据中心ID
     * 不同的项目可使用不同的参数,参数相同因雪花算法也会生成唯一id
     */
    public static Snowflake snowflake = IdUtil.createSnowflake(1, 1);

    /**
     * 系统使用的直接获取雪花算法唯一id的方法
     */
    public static long getSnowFlakeId(){
       return snowflake.nextId();
    }

    /**
     * 批量po对象转vo的方法
     */
    public static List copyList (List <? extends Object> poList , Class voClass){
        List voList= CollUtil.newArrayList();
        Object voObj =null;
        for(Object poObj:poList){
            try {
                voObj = voClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(poObj, voObj);
            voList.add(voObj);
        }
        return voList;
    }

    /**
     * @Description: 密码加密公共处理方法
     * @Author:      wgl
     * @Date:        2019/12/31 17:45
     * @Param:       [pass]
     * @return:
     */
    public static String passEncryption(String pass){
        return SecureUtil.md5(PASS_SALF + pass);
    }
}
