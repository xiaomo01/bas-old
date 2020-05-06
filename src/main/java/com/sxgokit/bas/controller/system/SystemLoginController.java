package com.sxgokit.bas.controller.system;

import cn.hutool.json.JSONObject;
import com.sxgokit.bas.base.DataPool;
import com.sxgokit.bas.base.ResultBody;
import com.sxgokit.bas.base.TokenComponent;
import com.sxgokit.bas.controller.BaseController;
import com.sxgokit.bas.entity.dto.system.SystemAdminDTO;
import com.sxgokit.bas.service.system.SystemAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public ResultBody<String> login(
            @ApiParam(name = "loginName", value = "用户登录名称", required = true)@RequestParam String loginName
            , @ApiParam(name = "loginPass", value = "用户登录密码", required = true)@RequestParam String loginPass) {
        SystemAdminDTO dto = SystemAdminDTO.builder()
                .loginName(loginName)
                .loginPass(DataPool.passEncryption(loginPass))
                .adminState(1)//数据字典标识，待处理
                .build();
        SystemAdminDTO model = systemAdminService.login(dto);
        if(model == null){
            return ResultBody.error("登录失败！请重新验证用户名与密码！");
        }else{
            //组装VO
            //获取用户信息，获取用户所属组织机构信息
            //返回vo，根据用户id，获取组织机构ids，去重获取所有本级及下级ids
            //获取roles
            //根据ids，获取所有permisson，去重获取所有
            JSONObject json = new JSONObject();
            String token = tokenComponent.createToken(model);
            json.put("token",token) ;
            return ResultBody.success(json);
        }
    }

}
