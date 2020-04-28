package com.sxgokit.bas.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sxgokit.bas.entity.dto.system.SystemPermissionDTO;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemPermissionService.java
 * @Description：
 * @createTime 2019年10月29日 10:55:00
 */
public interface SystemPermissionService extends IService<SystemPermissionDTO> {

    boolean savePermissionInfo(SystemPermissionDTO dto) throws Exception;
}
