package com.sxgokit.bas.entity.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SystemSensitiveVO
 * @Description: 敏感词信息返回
 * @Author: Administrator
 * @Date: 2019/12/30 16:42
 * @Version: 1.0
 */
@Data
public class SystemSensitiveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 敏感词内容
     */
    private String sensitiveContent;

    /**
     * 替换内容
     */
    private String relpaceContent;

    /**
     * 排序号(升序)
     */
    private Integer sortNum;
}
