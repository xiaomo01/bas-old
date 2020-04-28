package com.sxgokit.bas;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxgokit.bas.entity.dto.system.SystemConfigDTO;
import com.sxgokit.bas.service.system.SystemConfigService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName JunitDemo.java
 * @Description：junit测试类demo
 * 可直接通过@Autowired完成service等的注入
 * 开发时建议分包进行编写单元测试
 * @createTime 2019年11月04日 19:35:00
 */
public class JunitDemo extends BaseBasApplication{

    @Autowired
    private SystemConfigService systemConfigService;

    @Test
    public void Test_sysConfigPageList(){
        Page<SystemConfigDTO> page = new Page<>(1, 1);
        IPage<SystemConfigDTO> selectPageInfo =  systemConfigService.page(page, null);

        System.out.println(selectPageInfo.toString());

        System.out.println("-------------------");
        System.out.println("selectPageInfo总条数："+ selectPageInfo.getTotal());
        System.out.println("selectPageInfo当前页："+ selectPageInfo.getCurrent());
        System.out.println("selectPageInfo总页码："+ selectPageInfo.getPages());
        System.out.println("selectPageInfo每页多少条："+ selectPageInfo.getSize());
        System.out.println("selectPageInfo数据结果"+ selectPageInfo.getRecords().toString());
        System.out.println("-------------------");
        System.out.println("page总条数："+ page.getTotal());
        System.out.println("page当前页："+ page.getCurrent());
        System.out.println("page总页码："+ page.getPages());
        System.out.println("page每页多少条："+ page.getSize());
        System.out.println("page是否有上一条"+ page.hasPrevious());
        System.out.println("page是否有下一条"+ page.hasNext());
        System.out.println("page数据结果"+ page.getRecords().toString());
        System.out.println("-------------------");
        Assert.assertTrue(true);

    }

    @Test
    public void Test_sysConfigInsert(){
        SystemConfigDTO model = new SystemConfigDTO();
        model.setId(new Long("1191344925401288704"));
        model.setConfigKey("junit_test_2");
        model.setConfigValue("2");
        System.out.println(systemConfigService.saveOrUpdate(model));
        System.out.println("ID是:"+model.getId());
        Assert.assertTrue(true);
    }
}
