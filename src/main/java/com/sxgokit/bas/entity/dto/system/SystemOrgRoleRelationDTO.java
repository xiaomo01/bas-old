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
@TableName("system_org_role_relation")
@ApiModel(value = "systemOrgRoleRelation对象", description = "组织角色关系表对象")
public class SystemOrgRoleRelationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组织机构id
     */
    private Long orgId;

    /**
     * 角色id
     */
    private Long roleId;

}


