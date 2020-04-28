package com.sxgokit.bas.entity.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SystemDictionaryVO
 * @Description: 系统配置表返回
 * @Author: Administrator
 * @Date: 2019/12/30 16:42
 * @Version: 1.0
 */
@Data
public class SystemConfigVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private long id;

    /**
     * 配置属性编码
     */
    private String configKey;

    /**
     * 配置数据
     */
    private String configValue;

    /**
     * 配置说明
     */
    private String configContent;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序号(升序)
     */
    private Integer sortNum;
}
