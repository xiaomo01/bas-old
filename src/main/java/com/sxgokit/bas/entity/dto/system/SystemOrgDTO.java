package com.sxgokit.bas.entity.dto.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sxgokit.bas.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 组织机构信息表
 *
 * @author wgl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_org")
@ApiModel(value = "systemOrg对象", description = "组织机构信息表对象")
public class SystemOrgDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称", name = "orgName", example = "test_orgName", required = true)
    private String orgName;

    /**
     * 机构级别
     */
    @ApiModelProperty(value = "机构级别", hidden = true)
    private Integer orgGrade;

    /**
     * 上级机构编码
     */
    @ApiModelProperty(value = "上级机构编码", name = "orgParentId", example = "test_orgParentId", required = true)
    private Long orgParentId;

    /**
     * 所有机构编码
     */
    @ApiModelProperty(value = "所有机构编码", hidden = true)
    private String allOrgCodes;

    /**
     * 机构描述
     */
    @ApiModelProperty(value = "机构描述", name = "remark", example = "test_remark")
    private String remark;

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

    /**
     * 组织机构对应角色ID
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "组织机构对应角色ID", name = "roleId", example = "test_roleId")
    private Long roleId;

    /**
     * 组织机构关联用户ID数组
     */
    @TableField(exist = false)
    @ApiModelProperty(value="组织机构关联用户ID数组", name="adminIdArray", example="test_adminIdArray")
    private Long[] adminIdArray;
}


