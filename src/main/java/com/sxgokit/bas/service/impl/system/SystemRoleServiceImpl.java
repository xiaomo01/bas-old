package com.sxgokit.bas.service.impl.system;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxgokit.bas.entity.dto.system.SystemRoleDTO;
import com.sxgokit.bas.entity.dto.system.SystemRolePermissionRelationDTO;
import com.sxgokit.bas.mapper.system.SystemRoleMapper;
import com.sxgokit.bas.mapper.system.SystemRolePermissionRelationMapper;
import com.sxgokit.bas.service.system.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemRoleServiceImpl.java
 * @Description：
 * @createTime 2019年10月29日 10:56:00
 */
@Service
@Transactional(propagation= Propagation.NESTED,isolation= Isolation.DEFAULT,readOnly = false,rollbackFor=Exception.class)
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRoleDTO> implements SystemRoleService {

    /**
     * 用户与组织机构关系Mapper
     */
    @Autowired
    private SystemRolePermissionRelationMapper systemRolePermissionRelationMapper;

    @Override
    public boolean saveRoleInfo(SystemRoleDTO dto) throws Exception{
        boolean result;
        if(dto.getId() != null){
            result = baseMapper.updateById(dto) == 1 ? true : false;
        }else{
            result = baseMapper.insert(dto) == 1 ? true : false;
        }
        if(dto.getPermissionIdArray() != null && dto.getPermissionIdArray().length > 0){
            //清除原角色与菜单权限关联信息
            QueryWrapper<SystemRolePermissionRelationDTO> wrapper = new QueryWrapper<>();
            wrapper.eq("role_id",dto.getId());
            systemRolePermissionRelationMapper.delete(wrapper);
            //重新保存角色与菜单权限关联信息
            SystemRolePermissionRelationDTO relationDTO = null;
            for (Long permissionId : dto.getPermissionIdArray()) {
                relationDTO = new SystemRolePermissionRelationDTO();
                relationDTO.setRoleId(dto.getId());
                relationDTO.setPermissionId(permissionId);
                systemRolePermissionRelationMapper.insert(relationDTO);
            }
        }
        return result;
    }
}
