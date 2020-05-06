package com.sxgokit.bas.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxgokit.bas.entity.dto.system.SystemAdminDTO;
import com.sxgokit.bas.entity.vo.system.SystemAdminVO;
import com.sxgokit.bas.entity.vo.system.SystemPermissionVO;

import java.util.List;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemAdminService.java
 * @Description：
 * @createTime 2019年10月29日 10:55:00
 */
public interface SystemAdminService extends IService<SystemAdminDTO> {

    boolean saveAdminInfo(SystemAdminDTO dto) throws Exception;

    List<SystemAdminDTO> findPageList(IPage<SystemAdminDTO> iPage, SystemAdminDTO model);

    /**
     * 验证用户登录信息
     * @param dto
     * @return
     */
    SystemAdminDTO login(SystemAdminDTO dto);

    /**
     * 根据用户ID获取用户所有信息
     * @param id
     * @return
     */
    SystemAdminVO getAdminInfoById(Long id);

    /**
     * 根据用户ID获取所有本级及下级组织机构ID数组
     * @param id
     * @return
     */
    Long[] getOrgIdArrayById(Long id);

    /**
     * 根据组织机构ids获取所有角色ids
     * @param orgIdArray
     * @return
     */
    Long[] getRoleIdArrayById(Long[] orgIdArray);

    /**
     * 根据角色ids获取所有菜单权限
     * @param roleIdArray
     * @return
     */
    List<SystemPermissionVO> getPermissonListByRole(Long[] roleIdArray);
}