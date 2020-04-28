package com.sxgokit.bas.entity.dto.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sxgokit.bas.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据字典信息表
 *
 * @author wgl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_dictionary")
@ApiModel(value = "systemDictionary对象", description = "数据字典信息表对象")
public class SystemDictionaryDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 类型编码
     */
    @ApiModelProperty(value = "类型编码", name = "dictCode", example = "test_dictCode", required = true)
    private String dictCode;

    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称", name = "dictName", example = "test_dictName", required = true)
    private String dictName;

    /**
     * 数据标签
     */
    @ApiModelProperty(value = "数据标签", name = "dictLabel", example = "test_dictLabel", required = true)
    private String dictLabel;

    /**
     * 数据值编码
     */
    @ApiModelProperty(value = "数据值编码", name = "dictValueCode", example = "test_dictValueCode", required = true)
    private String dictValueCode;

    /**
     * 数据值
     */
    @ApiModelProperty(value = "数据值", name = "dictValue", example = "test_dictValue", required = true)
    private Integer dictValue;


    /**
     * 排序号(升序)
     */
    @ApiModelProperty(value = "排序号(升序)", name = "sortNum", example = "test_sortNum")
    private Integer sortNum;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", example = "test_remark")
    private String remark;

    /**
     * 创建用户
     */
    @ApiModelProperty(value = "创建用户", hidden = true)
    private Long createUser;

    /**
     * 更新用户
     */
    @ApiModelProperty(value = "更新用户", hidden = true)
    private Long updateUser;

}

