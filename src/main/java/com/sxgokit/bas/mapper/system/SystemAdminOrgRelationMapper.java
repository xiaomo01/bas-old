package com.sxgokit.bas.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgokit.bas.entity.dto.system.SystemAdminOrgRelationDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author dukang
 * @date 2019/06/05
 */
@Repository
public interface SystemAdminOrgRelationMapper extends BaseMapper<SystemAdminOrgRelationDTO> {

    @Select("SELECT * FROM " +
            "  (SELECT GROUP_CONCAT(DISTINCT org_id SEPARATOR ',') AS orgIdArray " +
            "    FROM system_admin_org_relation " +
            "    WHERE admin_id = #{userId}) a" +
            "  INNER JOIN" +
            "  (SELECT GROUP_CONCAT(DISTINCT id SEPARATOR ',') AS orgAllIdArray " +
            "    FROM system_org " +
            "    WHERE all_org_codes REGEXP " +
            "      (SELECT GROUP_CONCAT(DISTINCT org_id SEPARATOR '|') AS orgIdArray " +
            "        FROM system_admin_org_relation " +
            "        WHERE admin_id = #{userId})) b")
    Map<String, String> selectOrgBySql(@Param("userId") Long userId);

}
