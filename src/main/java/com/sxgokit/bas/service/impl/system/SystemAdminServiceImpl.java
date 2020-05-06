package com.sxgokit.bas.service.impl.system;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxgokit.bas.base.DataPool;
import com.sxgokit.bas.entity.dto.system.SystemAdminDTO;
import com.sxgokit.bas.entity.dto.system.SystemAdminOrgRelationDTO;
import com.sxgokit.bas.entity.dto.system.SystemOrgRoleRelationDTO;
import com.sxgokit.bas.entity.vo.system.SystemAdminVO;
import com.sxgokit.bas.entity.vo.system.SystemPermissionVO;
import com.sxgokit.bas.mapper.system.SystemAdminMapper;
import com.sxgokit.bas.mapper.system.SystemAdminOrgRelationMapper;
import com.sxgokit.bas.mapper.system.SystemOrgRoleRelationMapper;
import com.sxgokit.bas.mapper.system.SystemPermissionMapper;
import com.sxgokit.bas.service.system.SystemAdminService;
import com.sxgokit.bas.util.iterables.Iterables;
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
 * @ClassName SystemAdminServiceImpl.java
 * @Description：
 * @createTime 2019年12月26日 14:44:00
 */
@Service
@Transactional(propagation= Propagation.NESTED,isolation= Isolation.DEFAULT,readOnly = false,rollbackFor=Exception.class)
public class SystemAdminServiceImpl extends ServiceImpl<SystemAdminMapper, SystemAdminDTO> implements SystemAdminService {

    /**
     * 用户与组织机构关系Mapper
     */
    @Autowired
    private SystemAdminOrgRelationMapper systemAdminOrgRelationMapper;

    /**
     * 组织机构与角色关系Mapper
     */
    @Autowired
    private SystemOrgRoleRelationMapper systemOrgRoleRelationMapper;

    /**
     * 菜单权限Mapper
     */
    @Autowired
    private SystemPermissionMapper systemPermissionMapper;

    @Override
    public boolean saveAdminInfo(SystemAdminDTO dto) throws Exception{
        //针对用户密码进行加密处理，如用户未设置密码，则保存初始化默认密码
        if(StringUtils.isNotBlank(dto.getLoginPass())){
            dto.setLoginPass(DataPool.passEncryption(dto.getLoginPass()));
        }else{
            dto.setLoginPass(DataPool.LOGIN_PASS);
        }
        boolean result = dto.getId() == null ? (baseMapper.updateById(dto) == 1 ? true : false) : (baseMapper.insert(dto) == 1 ? true : false);
        //保存用户与组织机构关联信息
        if(dto.getOrgIdArray() != null && dto.getOrgIdArray().length > 0){
            //清除原用户与组织机构关联信息
            QueryWrapper<SystemAdminOrgRelationDTO> wrapper = new QueryWrapper<>();
            wrapper.eq("admin_id",dto.getId());
            systemAdminOrgRelationMapper.delete(wrapper);
            //重新保存用户与组织机构关联信息
            SystemAdminOrgRelationDTO relationDTO = null;
            for (Long orgId : dto.getOrgIdArray()) {
                relationDTO = new SystemAdminOrgRelationDTO();
                relationDTO.setAdminId(dto.getId());
                relationDTO.setOrgId(orgId);
                systemAdminOrgRelationMapper.insert(relationDTO);
            }
        }
        return result;
    }

    /**
     * 虚假的功能需要完善
     */
    @Override
    public List<SystemAdminDTO> findPageList(IPage<SystemAdminDTO> iPage, SystemAdminDTO dto) {
        QueryWrapper<SystemAdminDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(dto.getLoginName()), "login_name", dto.getLoginName());
        queryWrapper.like(StringUtils.isNotBlank(dto.getAdminName()), "admin_name", dto.getAdminName());
        queryWrapper.eq(dto.getAdminState() != null, "admin_state", dto.getAdminState());
//        return baseMapper.getList(iPage, queryWrapper);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public SystemAdminDTO login(SystemAdminDTO dto) {
        Map<String, Object> result = new HashMap<String, Object>();
        QueryWrapper<SystemAdminDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", dto.getLoginName());
        queryWrapper.eq("login_pass", dto.getLoginPass());
        queryWrapper.eq("admin_state", dto.getAdminState());
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public SystemAdminVO getAdminInfoById(Long id) {
        return baseMapper.getAdminInfoById(id);
    }

    @Override
    public Long[] getOrgIdArrayById(Long id) {
        return baseMapper.getOrgIdArrayById(id);
    }

    @Override
    public Long[] getRoleIdArrayById(Long[] orgIdArray) {
        QueryWrapper<SystemOrgRoleRelationDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("org_id",orgIdArray);
        List<SystemOrgRoleRelationDTO> list = systemOrgRoleRelationMapper.selectList(queryWrapper);
        if(CollectionUtil.isNotEmpty(list)){
            Long[] roleIdArray = new Long[list.size()];
            Iterables.forEach(list, (index, dto) -> roleIdArray[index] = dto.getRoleId());
            return roleIdArray;
        }
        return null;
    }

    @Override
    public List<SystemPermissionVO> getPermissonListByRole(Long[] roleIdArray) {
        return systemPermissionMapper.getPermissonListByRole(roleIdArray);
    }
}
