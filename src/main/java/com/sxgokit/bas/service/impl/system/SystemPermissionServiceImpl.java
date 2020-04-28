package com.sxgokit.bas.service.impl.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxgokit.bas.entity.dto.system.SystemPermissionDTO;
import com.sxgokit.bas.mapper.system.SystemPermissionMapper;
import com.sxgokit.bas.service.system.SystemPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemPermissionServiceImpl.java
 * @Description：
 * @createTime 2019年10月29日 10:56:00
 */
@Service
@Transactional(propagation= Propagation.NESTED,isolation= Isolation.DEFAULT,readOnly = false,rollbackFor=Exception.class)
public class SystemPermissionServiceImpl extends ServiceImpl<SystemPermissionMapper, SystemPermissionDTO> implements SystemPermissionService {

    @Override
    public boolean savePermissionInfo(SystemPermissionDTO dto) throws Exception {
        boolean result;
        if (dto.getId() != null) {
            result = baseMapper.updateById(dto) == 1 ? true : false;
        } else {
            result = baseMapper.insert(dto) == 1 ? true : false;
            //保存完成后，设置所有菜单编码及级别进行更新
            if (dto.getPermissionParentId() == null || dto.getPermissionParentId().intValue() == 0) {
                dto.setPermissionParentId((long) 0);
                dto.setPermissionGrade(1);
                dto.setAllPermissionIds(",0," + dto.getId() + ",");
            } else {
                SystemPermissionDTO parentPermission = baseMapper.selectById(dto.getPermissionParentId());
                if (parentPermission != null) {
                    dto.setPermissionGrade(parentPermission.getPermissionGrade() + 1);
                    dto.setAllPermissionIds(parentPermission.getAllPermissionIds() + dto.getId() + ",");
                }
            }
            baseMapper.updateById(dto);
        }
        return result;
    }
}
