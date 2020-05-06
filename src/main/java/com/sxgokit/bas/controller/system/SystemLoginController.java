package com.sxgokit.bas.controller.system;

import cn.hutool.json.JSONObject;
import com.sxgokit.bas.base.DataPool;
import com.sxgokit.bas.base.ResultBody;
import com.sxgokit.bas.base.TokenComponent;
import com.sxgokit.bas.controller.BaseController;
import com.sxgokit.bas.entity.dto.system.SystemAdminDTO;
import com.sxgokit.bas.entity.vo.system.SystemAdminVO;
import com.sxgokit.bas.entity.vo.system.SystemPermissionVO;
import com.sxgokit.bas.service.system.SystemAdminService;
import com.sxgokit.bas.util.array.ArrayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author wgl
 * @version 1.0.0
 * @ClassName SystemAdminController.java
 * @createTime 2019年10月26日 02:20:00
 */
@RestController
@RequestMapping("system/login")
@Api(tags = {"系统用户登录"})
public class SystemLoginController extends BaseController {

    @Autowired
    private SystemAdminService systemAdminService;

    @Resource
    private TokenComponent tokenComponent;

    @ApiOperation(value = "用户登录", notes = "系统用户登录")
    @PostMapping(value = "/",consumes = "application/x-www-form-urlencoded")
    public ResultBody<SystemAdminVO> login(
            @ApiParam(name = "loginName", value = "用户登录名称", required = true)@RequestParam String loginName
            , @ApiParam(name = "loginPass", value = "用户登录密码", required = true)@RequestParam String loginPass) {
        SystemAdminDTO dto = SystemAdminDTO.builder()
                .loginName(loginName)
                .loginPass(DataPool.passEncryption(loginPass))
                .adminState(1)//数据字典标识，待处理
                .build();
        SystemAdminDTO adminDTO = systemAdminService.login(dto);
        if(adminDTO == null){
            return ResultBody.error("登录失败！请重新验证用户名与密码！");
        }else{
            //组装VO,获取用户信息，获取用户所属组织机构信息
            SystemAdminVO systemAdminVO = systemAdminService.getAdminInfoById(adminDTO.getId());
            //根据用户id，获取组织机构ids(去重获取所有本级及下级ids)
            Long[] orgIdArray = systemAdminService.getOrgIdArrayById(adminDTO.getId());
            if(orgIdArray != null && orgIdArray.length != 0){
                //数组去重
                orgIdArray = ArrayUtil.removeDupl(orgIdArray);
                systemAdminVO.setOrgIdArray(orgIdArray);
                //根据组织机构ids 获取角色 roleIds
                Long[] roleIdArray = systemAdminService.getRoleIdArrayById(orgIdArray);
                if(roleIdArray != null && roleIdArray.length != 0){
                    //数组去重
                    roleIdArray = ArrayUtil.removeDupl(roleIdArray);
                    systemAdminVO.setRoleIdArray(roleIdArray);
                    //根据角色Id，获取所有菜单权限集合
                    List<SystemPermissionVO> permissionVOList = systemAdminService.getPermissonListByRole(roleIdArray);
                    systemAdminVO.setPermissionVOList(permissionVOList);
                    String token = tokenComponent.createToken(systemAdminVO);
                    systemAdminVO.setToken(token);
                    return ResultBody.success(systemAdminVO);
                }else {
                    return ResultBody.error("登录失败！该用户所属组织机构未绑定角色，请联系系统管理员！");
                }
            }else {
                return ResultBody.error("登录失败！该用户未绑定组织机构，请联系系统管理员！");
            }
        }
    }
}
