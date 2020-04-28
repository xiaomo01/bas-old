package com.sxgokit.bas.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sxgokit.bas.entity.dto.system.SystemRoleDTO;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemRoleService.java
 * @Description：
 * @createTime 2019年10月29日 10:55:00
 */
public interface SystemRoleService extends IService<SystemRoleDTO> {

    /**
     * @Description: 保存系统角色信息
     * @Author:      wgl
     * @Date:        2020/1/2 15:09
     * @Param:       [dto]
     * @return:      boolean
     */
    boolean saveRoleInfo(SystemRoleDTO dto) throws Exception;
}
