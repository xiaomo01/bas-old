package com.sxgokit.bas.entity.dto.system;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 权限信息表
 *
 * @author wgl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_permission")
@ApiModel(value = "systemPermission对象", description = "权限信息表对象")
public class SystemPermissionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "id,新增不需上传", name = "id", example = "null")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "permissionName", example = "test_permissionName", required = true)
    private String permissionName;

    /**
     * 上级编码
     */
    @ApiModelProperty(value = "上级编码", name = "permissionParentId", example = "test_permissionParentId", required = true)
    private Long permissionParentId;

    /**
     * 级别编码
     */
    @ApiModelProperty(value = "级别编码", hidden = true)
    private Integer permissionGrade;

    /**
     * 类型（菜单、功能）
     */
    @ApiModelProperty(value = "类型（菜单、功能）", name = "permissionType", example = "test_permissionType", required = true)
    private Integer permissionType;

    /**
     * 是否底层菜单（用于判断是否可添加下级菜单）
     */
    @ApiModelProperty(value = "是否底层菜单", name = "lastFlag", example = "test_lastFlag", required = true)
    private Integer lastFlag;

    /**
     * 菜单链接
     */
    @ApiModelProperty(value = "菜单链接", name = "permissionUrl", example = "test_permissionUrl", required = true)
    private String permissionUrl;

    /**
     * 所有功能编码
     */
    @ApiModelProperty(value = "所有功能编码", hidden = true)
    private String allPermissionIds;

    /**
     * 排序号(升序)
     */
    @ApiModelProperty(value = "排序号(升序)", name = "sortNum", example = "test_sortNum")
    private Integer sortNum;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "remark", example = "test_remark")
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
    @ApiModelProperty(value = "创建时间", hidden = true)
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
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updateTime;

    /**
     * 菜单下功能集合
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "菜单下功能集合", hidden = true)
    private List<SystemPermissionDTO> functionList;
}


