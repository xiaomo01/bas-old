package com.sxgokit.bas.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxgokit.bas.common.exception.GlobalExceptionHandler;
import com.sxgokit.bas.entity.dto.system.SystemAdminDTO;
import org.springframework.transaction.annotation.Transactional;

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

    SystemAdminDTO login(SystemAdminDTO dto);
}