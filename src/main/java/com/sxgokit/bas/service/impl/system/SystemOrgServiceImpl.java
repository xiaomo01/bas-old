package com.sxgokit.bas.service.impl.system;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.sxgokit.bas.entity.dto.system.*;
import com.sxgokit.bas.entity.vo.common.UserPermissionVO;
import com.sxgokit.bas.mapper.system.SystemAdminOrgRelationMapper;
import com.sxgokit.bas.mapper.system.SystemOrgMapper;
import com.sxgokit.bas.mapper.system.SystemOrgRoleRelationMapper;
import com.sxgokit.bas.mapper.system.SystemRolePermissionRelationMapper;
import com.sxgokit.bas.service.system.SystemOrgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemOrgServiceImpl.java
 * @Description：
 * @createTime 2019年10月29日 10:56:00
 */
@Service
@Transactional(propagation = Propagation.NESTED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
public class SystemOrgServiceImpl extends ServiceImpl<SystemOrgMapper, SystemOrgDTO> implements SystemOrgService {

    @Autowired
    private SystemOrgRoleRelationMapper systemOrgRoleRelationMapper;

    @Autowired
    private SystemAdminOrgRelationMapper systemAdminOrgRelationMapper;

    @Autowired
    private SystemRolePermissionRelationMapper systemRolePermissionRelationMapper;

    @Override
    public boolean saveOrgInfo(SystemOrgDTO dto) throws Exception {
        boolean result;
        if (dto.getId() != null) {
            result = baseMapper.updateById(dto) == 1 ? true : false;
        } else {
            result = baseMapper.insert(dto) == 1 ? true : false;
            //保存完成后，设置所有机构编码及级别进行更新
            if (dto.getOrgParentId() == null || dto.getOrgParentId().intValue() == 0) {
                dto.setOrgGrade(1);
                dto.setAllOrgCodes(",0," + dto.getId() + ",");
            } else {
                SystemOrgDTO parentOrg = baseMapper.selectById(dto.getOrgParentId());
                if (parentOrg != null) {
                    dto.setOrgGrade(parentOrg.getOrgGrade() + 1);
                    dto.setAllOrgCodes(parentOrg.getAllOrgCodes() + dto.getId() + ",");
                }
            }
            baseMapper.updateById(dto);
        }
        if (dto.getRoleId() != null) {
            //清除原组织机构与角色关联信息
            QueryWrapper<SystemOrgRoleRelationDTO> wrapper = new QueryWrapper<>();
            wrapper.eq("org_id", dto.getId());
            systemOrgRoleRelationMapper.delete(wrapper);
            //重新保存组织机构与角色关联信息
            SystemOrgRoleRelationDTO relationDTO = new SystemOrgRoleRelationDTO();
            relationDTO.setOrgId(dto.getId());
            relationDTO.setRoleId(dto.getRoleId());
            systemOrgRoleRelationMapper.insert(relationDTO);
        }
        if (dto.getAdminIdArray() != null && dto.getAdminIdArray().length > 0) {
            //清除原组织机构与用户关联信息
            QueryWrapper<SystemAdminOrgRelationDTO> wrapper = new QueryWrapper<>();
            wrapper.eq("org_id", dto.getId());
            systemAdminOrgRelationMapper.delete(wrapper);
            //重新保存组织机构与用户关联信息
            SystemAdminOrgRelationDTO relationDTO = null;
            for (Long adminId : dto.getAdminIdArray()) {
                relationDTO = new SystemAdminOrgRelationDTO();
                relationDTO.setOrgId(dto.getId());
                relationDTO.setAdminId(adminId);
                systemAdminOrgRelationMapper.insert(relationDTO);
            }
        }
        return result;
    }

    @Override
    public UserPermissionVO getUserPermission(Long userId) {
        UserPermissionVO userPermissionVO = new UserPermissionVO();
        userPermissionVO.setUserId(userId);
        //根据用户ID，查询用户直属机构ID数组及所有机构及下级的ID数组
        Map<String, String> orgInfo = systemAdminOrgRelationMapper.selectOrgBySql(userId);
        if (StringUtils.isNotBlank(orgInfo.get("orgIdArray"))) {
            //用户直属组织机构ID数组
            String[] orgIdStrArray = orgInfo.get("orgIdArray").split(",");
            Long[] orgIdArray = Convert.toLongArray(orgIdStrArray);
            userPermissionVO.setOrgIdArray(orgIdArray);

            //用户所属所有组织机构及下级机构ID数组
            String[] orgAllIdStrArray = orgInfo.get("orgAllIdArray").split(",");
            Long[] orgAllIdArray = Convert.toLongArray(orgAllIdStrArray);
            userPermissionVO.setOrgAllIdArray(orgAllIdArray);

            //根据所有所有组织机构ID查询所有角色，再根据所有角色查询所有的权限菜单
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.in("org_id", orgAllIdArray);
            List<SystemPermissionDTO> permissionList = systemRolePermissionRelationMapper.getPermissionByOrg(wrapper);
            if (CollUtil.isNotEmpty(permissionList)) {
                for (SystemPermissionDTO permissionDTO : permissionList) {
                    //判断当前菜单为最底层菜单时，进行功能权限查询
                    if (permissionDTO.getLastFlag() == 1) {
                        List<SystemPermissionDTO> functionList = systemRolePermissionRelationMapper.getPermissionByParent(wrapper, permissionDTO.getId());
                        permissionDTO.setFunctionList(functionList);
                    }
                }
            }
            userPermissionVO.setPermissionList(permissionList);
        }
        return userPermissionVO;
    }
}
