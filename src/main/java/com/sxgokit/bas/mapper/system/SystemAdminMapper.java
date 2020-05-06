package com.sxgokit.bas.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgokit.bas.entity.dto.system.SystemAdminDTO;
import com.sxgokit.bas.entity.vo.system.SystemAdminVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author dukang
 * @date 2019/12/25
 */
@Repository
public interface SystemAdminMapper extends BaseMapper<SystemAdminDTO> {

    @Select("SELECT ad.admin_name, ad.login_name, adi.admin_id, adi.head_image_path, adi.admin_phone, adi.admin_sex, adi.admin_age, adi.admin_address,adi.remark " +
            "FROM system_admin ad LEFT JOIN system_admin_info adi ON adi.admin_id = ad.id " +
            "WHERE ad.id = #{id} ")
    SystemAdminVO getAdminInfoById(@Param("id") Long id);

    @Select("SELECT org.id FROM system_org org " +
            "WHERE all_org_codes " +
            "REGEXP (SELECT GROUP_CONCAT(org_id separator '|') " +
            "        FROM system_admin_org_relation " +
            "        WHERE admin_id = #{id})")
    Long[] getOrgIdArrayById(@Param("id") Long id);
}