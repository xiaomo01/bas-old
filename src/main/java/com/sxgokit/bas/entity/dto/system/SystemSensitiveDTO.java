package com.sxgokit.bas.entity.dto.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sxgokit.bas.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 敏感词信息表
 *
 * @author wgl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_sensitive")
@ApiModel(value = "systemSensitive对象", description = "敏感词信息表对象")
public class SystemSensitiveDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 敏感词内容
     */
    @ApiModelProperty(value = "敏感词内容", name = "sensitiveContent", example = "test_sensitiveContent", required = true)
    private String sensitiveContent;

    /**
     * 替换内容
     */
    @ApiModelProperty(value = "替换内容", name = "relpaceContent", example = "test_relpaceContent", required = true)
    private String relpaceContent;

    /**
     * 排序号(升序)
     */
    @ApiModelProperty(value = "排序号(升序)", name = "sortNum", example = "test_sortNum")
    private Integer sortNum;

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


