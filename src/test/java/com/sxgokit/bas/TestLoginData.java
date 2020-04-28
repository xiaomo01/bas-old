package com.sxgokit.bas;

import cn.hutool.core.collection.CollUtil;
import com.sxgokit.bas.entity.dto.system.SystemPermissionDTO;
import com.sxgokit.bas.entity.vo.common.UserPermissionVO;
import com.sxgokit.bas.service.system.SystemOrgService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TestLoginData
 * @Description: 类描述
 * @Author: Administrator
 * @Date: 2020/1/3 11:28
 * @Version: 1.0
 */
public class TestLoginData extends BaseBasApplication {

    @Autowired
    private SystemOrgService systemOrgService;

    @Test
    public void getUserPermission() {
        Long userId = new Long("1211949375159906306");
        UserPermissionVO userPermissionVO = systemOrgService.getUserPermission(userId);
        for (int i = 0; i < userPermissionVO.getOrgIdArray().length; i++) {
            System.out.println("orgIdArray["+i+"]=="+userPermissionVO.getOrgIdArray()[i]);
        }
        for (int i = 0; i < userPermissionVO.getOrgAllIdArray().length; i++) {
            System.out.println("orgAllIdArray["+i+"]=="+userPermissionVO.getOrgAllIdArray()[i]);
        }
        List<SystemPermissionDTO> permissionList = userPermissionVO.getPermissionList();
        for (SystemPermissionDTO permissionDTO : permissionList) {
            System.out.println("菜单名称="+permissionDTO.getPermissionName());
            if(CollUtil.isNotEmpty(permissionDTO.getFunctionList())){
                for (SystemPermissionDTO dto : permissionDTO.getFunctionList()) {
                    System.out.println("功能名称="+dto.getPermissionName());
                }
            }
        }
    }
}
