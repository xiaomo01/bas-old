package com.sxgokit.bas.service.impl.common;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxgokit.bas.entity.dto.common.CommonRegionInfoDTO;
import com.sxgokit.bas.mapper.common.CommonRegionInfoMapper;
import com.sxgokit.bas.service.common.CommonRegionInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName CommonRegionInfoServiceImpl.java
 * @Description：
 * @createTime 2019年10月29日 10:56:00
 */
@Service
@Transactional(propagation = Propagation.NESTED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
public class CommonRegionInfoServiceImpl extends ServiceImpl<CommonRegionInfoMapper, CommonRegionInfoDTO> implements CommonRegionInfoService {

    @Override
    public boolean saveRegionInfo(CommonRegionInfoDTO dto) throws Exception {
        boolean result;
        if (dto.getId() != null) {
            result = baseMapper.updateById(dto) == 1 ? true : false;
        } else {
            result = baseMapper.insert(dto) == 1 ? true : false;
            //保存完成后，设置所有行政区划编码、级别、名称进行更新
            if (dto.getRegionParentId() == null || dto.getRegionParentId().intValue() == 0) {
                dto.setRegionParentId((long) 0);
                dto.setRegionParentCode(0);
                dto.setRegionGrade(1);
                dto.setAllRegionIds(",0," + dto.getId() + ",");
                dto.setAllRegionCodes(",0," + dto.getRegionCode() + ",");
                dto.setAllRegionNames(dto.getRegionName());
            } else {
                CommonRegionInfoDTO parentRegion = baseMapper.selectById(dto.getRegionParentId());
                if (parentRegion != null) {
                    dto.setRegionParentCode(parentRegion.getRegionCode());
                    dto.setRegionGrade(parentRegion.getRegionGrade() + 1);
                    dto.setAllRegionIds(parentRegion.getAllRegionIds() + dto.getId() + ",");
                    dto.setAllRegionCodes(parentRegion.getAllRegionCodes() + dto.getRegionCode() + ",");
                    dto.setAllRegionNames(parentRegion.getAllRegionNames() + dto.getRegionName());
                }
            }
            baseMapper.updateById(dto);
        }
        return result;
    }
}
