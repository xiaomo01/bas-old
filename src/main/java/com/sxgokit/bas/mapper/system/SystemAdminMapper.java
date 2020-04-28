package com.sxgokit.bas.mapper.system;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.sxgokit.bas.entity.dto.system.SystemAdminDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author dukang
 * @date 2019/12/25
 */
@Repository
public interface SystemAdminMapper extends BaseMapper<SystemAdminDTO> {

//    @Select("SELECT *," +
//            "(SELECT ROLE_NAME FROM SYSTEM_ROLE WHERE ROLE_ID = (SELECT ROLE_ID FROM SYSTEM_ADMIN_ROLE WHERE ADMIN_ID = SA.ADMIN_ID)) AS ROLE_NAME" +
//            " FROM SYSTEM_ADMIN SA " +
//            "${ew.customSqlSegment}")
//    List<SystemAdminDTO> getList(IPage<SystemAdminDTO> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}