package com.sxgokit.bas.entity.dto.system;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 系统角色信息表
 *
 * @author wgl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_role_permission_relation")
@ApiModel(value = "systemRolePermissionRelation对象", description = "角色权限关系表对象")
public class SystemRolePermissionRelationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 功能id
     */
    private Long permissionId;

}


