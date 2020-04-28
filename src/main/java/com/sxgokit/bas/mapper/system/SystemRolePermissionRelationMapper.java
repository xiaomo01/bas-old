package com.sxgokit.bas.mapper.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.sxgokit.bas.entity.dto.system.SystemPermissionDTO;
import com.sxgokit.bas.entity.dto.system.SystemRolePermissionRelationDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dukang
 * @date 2019/06/05
 */
@Repository
public interface SystemRolePermissionRelationMapper extends BaseMapper<SystemRolePermissionRelationDTO> {

    /**
     * @Description: 根据组织机构ID，获取相应角色相应的所有菜单权限集合
     * @Author:      wgl
     * @Date:        2020/1/3 14:36
     * @Param:       [orgArray]
     * @return:      java.util.List<com.sxgokit.bas.entity.dto.system.SystemPermissionDTO>
     */
    @Select("SELECT DISTINCT sp.* " +
            "FROM system_role_permission_relation spr " +
            "LEFT JOIN system_permission sp ON spr.permission_id = sp.id " +
            "WHERE sp.permission_type = 1 " +
            "AND spr.role_id IN (" +
            "   SELECT DISTINCT role_id " +
            "   FROM system_org_role_relation " +
            "   ${ew.customSqlSegment}" +
            ") ")
    List<SystemPermissionDTO> getPermissionByOrg(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    /**
     * @Description: 根据组织机构ID与上级菜单ID，获取所有功能权限集合
     * @Author:      wgl
     * @Date:        2020/1/3 14:36
     * @Param:       [orgArray]
     * @return:      java.util.List<com.sxgokit.bas.entity.dto.system.SystemPermissionDTO>
     */
    @Select("SELECT DISTINCT sp.* " +
            "FROM system_role_permission_relation spr " +
            "LEFT JOIN system_permission sp ON spr.permission_id = sp.id " +
            "WHERE sp.permission_type = 2 " +
            "AND sp.permission_parent_id = #{permissionId} " +
            "AND spr.role_id IN (" +
            "   SELECT DISTINCT role_id " +
            "   FROM system_org_role_relation " +
            "   ${ew.customSqlSegment}" +
            ") ")
    List<SystemPermissionDTO> getPermissionByParent(@Param(Constants.WRAPPER) QueryWrapper wrapper,@Param("permissionId") Long permissionId);

}
