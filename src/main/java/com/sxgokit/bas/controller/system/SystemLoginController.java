package com.sxgokit.bas.controller.system;

import com.sxgokit.bas.base.ResultBody;
import com.sxgokit.bas.controller.BaseController;
import com.sxgokit.bas.security.JwtTokenUtil;
import com.sxgokit.bas.service.system.SystemAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

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
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SystemAdminService systemAdminService;

    /*@ApiOperation(value = "用户登录", notes = "系统用户登录")
    @PostMapping(value = "/login")
    public ResultBody<String> login(
            @ApiParam(name = "loginName", value = "用户登录名称", required = true) @RequestParam(required = true) String loginName
            , @ApiParam(name = "loginPass", value = "用户登录密码", required = true) @RequestParam(required = true) String loginPass) {
        SystemAdminDTO dto = SystemAdminDTO.builder()
                .loginName(loginName)
                .loginPass(DataPool.passEncryption(loginPass))
                .adminState(1)//数据字典标识，待处理
                .build();
        SystemAdminDTO model = systemAdminService.login(dto);
        String result = "fail";
        if(model != null){
            result = "success";
        }
        return ResultBody.success(result);
    }*/

    @ApiOperation(value = "用户登录", notes = "系统用户登录")
    @PostMapping("/login")
    public ResultBody<String> login(@ApiParam(name = "loginName", value = "用户登录名称", required = true) @RequestParam(required = true) String loginName){
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginName);
        final String token = jwtTokenUtil.generateToken(userDetails);
        
        return ResultBody.success(token);
    }

}
