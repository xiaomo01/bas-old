package com.sxgokit.bas;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import cn.hutool.core.date.DateUtil;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 总Junit运行配置文件
 * @Author: Dukang
 * @Date: 2019/6/5/0005 11:11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class BaseBasApplication {

    @Before
    public void init() {
        System.out.println("---------------开始执行测试文件:"+ DateUtil.now() +"---------------");
    }

    @After
    public void after() {
        System.out.println("---------------测试文件执行完毕:"+ DateUtil.now() +"---------------");
    }
}
