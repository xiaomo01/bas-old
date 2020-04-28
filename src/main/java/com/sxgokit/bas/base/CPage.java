package com.sxgokit.bas.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import java.io.Serializable;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName CPage.java
 * @Description：自定义page返回信息
 * 使用MybatisPlus自带分页中Page信息较多,为避免造成前台数据冗余,进行重新构造
 * @createTime 2019年11月07日 17:23:00
 */
@Data
public class CPage implements Serializable {
    private long size;
    private long current;
    private long total;
    private long pages;

    CPage(IPage iPage){
        this.size = iPage.getSize();
        this.current = iPage.getCurrent();
        this.total = iPage.getTotal();
        this.pages = iPage.getPages();
    }
}
