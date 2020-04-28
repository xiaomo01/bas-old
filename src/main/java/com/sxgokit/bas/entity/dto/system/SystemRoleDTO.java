package com.sxgokit.bas.entity.dto.system;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色信息表
 *
 * @author wgl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_role")
@ApiModel(value = "systemRole对象", description = "系统角色信息表对象")
public class SystemRoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "id,新增不需上传", name = "id", example = "null")
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", name = "roleName", example = "test_roleName", required = true)
    private String roleName;

    /**
     * 排序号(升序)
     */
    @ApiModelProperty(value = "排序号(升序)", name = "sortNum", example = "test_sortNum")
    private Integer sortNum;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述", name = "remark", example = "test_remark")
    private String remark;

    /**
     * 创建用户
     */
    @ApiModelProperty(value = "创建用户", hidden = true)
    private Long createUser;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间", hidden = true)
    private Date createTime;

    /**
     * 更新用户
     */
    @ApiModelProperty(value = "更新用户", hidden = true)
    private Long updateUser;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value="更新时间", hidden = true)
    private Date updateTime;

    /**
     * 角色关联菜单权限ID数组
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "角色关联菜单权限ID数组", name = "permissionIdArray", example = "test_permissionIdArray")
    private Long[] permissionIdArray;
}


