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
@TableName("system_admin_org_relation")
@ApiModel(value = "systemAdminOrgRelation对象", description = "用户组织关系表对象")
public class SystemAdminOrgRelationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员id
     */
    private Long adminId;

    /**
     * 组织机构id
     */
    private Long orgId;

}


