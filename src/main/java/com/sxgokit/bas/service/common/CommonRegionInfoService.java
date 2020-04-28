package com.sxgokit.bas.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sxgokit.bas.entity.dto.common.CommonRegionInfoDTO;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName CommonRegionInfoService.java
 * @Description：
 * @createTime 2019年10月29日 10:55:00
 */
public interface CommonRegionInfoService extends IService<CommonRegionInfoDTO> {

    boolean saveRegionInfo(CommonRegionInfoDTO dto) throws Exception;
}
