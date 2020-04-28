package com.sxgokit.bas.entity.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SystemDictionaryVO
 * @Description: 数据字典表返回
 * @Author: Administrator
 * @Date: 2019/12/30 16:42
 * @Version: 1.0
 */
@Data
public class SystemDictionaryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 类型编码
     */
    private String dictCode;

    /**
     * 类型名称
     */
    private String dictName;

    /**
     * 数据标签
     */
    private String dictLabel;

    /**
     * 数据值编码
     */
    private String dictValueCode;;

    /**
     * 数据值
     */
    private Integer dictValue;

    /**
     * 排序号(升序)
     */
    private Integer sortNum;

    /**
     * 删除状态
     */
    private Integer delFlag;

    /**
     * 备注
     */
    private String remark;
}
