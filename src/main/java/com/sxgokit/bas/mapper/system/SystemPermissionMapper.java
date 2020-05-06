package com.sxgokit.bas.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgokit.bas.entity.dto.system.SystemPermissionDTO;
import com.sxgokit.bas.entity.vo.system.SystemPermissionVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dukang
 * @date 2019/06/05
 */
@Repository
public interface SystemPermissionMapper extends BaseMapper<SystemPermissionDTO> {

    /**
     * 根据角色id数组获取所有菜单权限集合
     * @param roleIdArray
     * @return
     */
    @Select({
            "<script>",
                "SELECT * FROM system_permission per ",
                "WHERE all_permission_ids ",
                "REGEXP (SELECT GROUP_CONCAT(permission_id separator '|') ",
                    "FROM system_role_permission_relation ",
                    "WHERE role_id IN",
                    "<foreach item='item' index='index' collection='roleIdArray' open='(' separator=',' close=')'>",
                        "#{item}",
                    "</foreach>",
                ")",
            "</script>"
    })
    List<SystemPermissionVO> getPermissonListByRole(@Param("roleIdArray") Long[] roleIdArray);

}
