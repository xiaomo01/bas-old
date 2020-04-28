package com.sxgokit.bas.entity.vo.common;

import com.sxgokit.bas.entity.dto.system.SystemPermissionDTO;
import lombok.Data;

import java.util.List;

/**
 * @ClassName:   UserPermissionVO
 * @Description: 用户数据、菜单权限信息实体
 * @Author:      Administrator
 * @Date:        2020/1/3 14:20
 * @Version:     1.0
 */
@Data
public class UserPermissionVO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户token
     */
    private String token;

    /**
     * 用户直属组织机构ID数组
     */
    private Long[] orgIdArray;

    /**
     * 用户所属所有组织机构及下级机构ID数组
     */
    private Long[] orgAllIdArray;

    /**
     * 用户对应组织对应角色的所有菜单权限集合（去重，内含功能权限集合）
     */
    private List<SystemPermissionDTO> permissionList;

}
