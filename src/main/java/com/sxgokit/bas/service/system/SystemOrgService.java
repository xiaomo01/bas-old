package com.sxgokit.bas.service.system;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxgokit.bas.entity.dto.system.SystemOrgDTO;
import com.sxgokit.bas.entity.vo.common.UserPermissionVO;

import java.util.Map;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemOrgService.java
 * @Description：
 * @createTime 2019年10月29日 10:55:00
 */
public interface SystemOrgService extends IService<SystemOrgDTO> {

    boolean saveOrgInfo(SystemOrgDTO dto) throws Exception;

    /**
     * @Description: 根据用户ID，获取用户直属机构ID数组，所属机构所有本级及下级ID数组，所有菜单权限集合（菜单中所有功能权限集合）
     * @Author: wgl
     * @Date: 2020/1/3 14:26
     * @Param: [userId]
     * @return: com.sxgokit.bas.entity.vo.common.UserPermissionVO
     */
    UserPermissionVO getUserPermission(Long userId);
}
