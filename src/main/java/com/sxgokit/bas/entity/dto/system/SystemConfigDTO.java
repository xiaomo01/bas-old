package com.sxgokit.bas.entity.dto.system;

import com.sxgokit.bas.base.BaseEntity;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModel;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统配置信息表
 *
 * @author wgl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_config")
@ApiModel(value = "systemConfig对象", description = "系统配置信息表对象")
public class SystemConfigDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 配置属性编码
     */
    @ApiModelProperty(value = "配置属性编码", name = "configKey", example = "test_configKey", required = true)
    private String configKey;

    /**
     * 配置说明
     */
    @ApiModelProperty(value = "配置说明", name = "configContent", example = "test_configContent", required = true)
    private String configContent;

    /**
     * 配置数据
     */
    @ApiModelProperty(value = "配置数据", name = "configValue", example = "test_configValue", required = true)
    private String configValue;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", example = "test_remark")
    private String remark;

    /**
     * 排序号(升序)
     */
    @ApiModelProperty(value = "排序号(升序)", name = "sortNum", example = "test_sortNum")
    private Integer sortNum;
}