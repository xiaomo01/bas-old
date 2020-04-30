package com.sxgokit.bas.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxgokit.bas.base.DataPool;
import com.sxgokit.bas.base.ResultBody;
import com.sxgokit.bas.controller.BaseController;
import com.sxgokit.bas.entity.dto.system.SystemAdminDTO;
import com.sxgokit.bas.service.system.SystemAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemAdminController.java
 * @createTime 2019年10月26日 02:20:00
 */
@RestController
@RequestMapping("system/admin")
@Api(tags = {"系统用户管理"})
public class SystemAdminController extends BaseController {

    @Autowired
    private SystemAdminService systemAdminService;

    @ApiOperation(value = "列表分页", notes = "系统用户列表分页")
    @PostMapping(value = "/getPageList/{currentPage}/{showSize}")
    public ResultBody<SystemAdminDTO> getPageList(
            @ApiParam(name = "currentPage", value = "当前页码", required = true, defaultValue = "1") @PathVariable int currentPage
            , @ApiParam(name = "showSize", value = "一页展示数", required = true, defaultValue = "10") @PathVariable int showSize
            , @ApiParam(name = "loginName", value = "登录名") @RequestParam(required = false) String loginName
            , @ApiParam(name = "adminName", value = "用户名") @RequestParam(required = false) String adminName
            , @ApiParam(name = "adminState", value = "用户状态1:启用;2:禁用") @RequestParam(required = false) Integer adminState
    ) {

        SystemAdminDTO systemAdminDTO = SystemAdminDTO.builder()
                .loginName(loginName)
                .adminName(adminName)
                .adminState(adminState).build();
        return ResultBody.success(systemAdminService.findPageList(new Page<>(currentPage, showSize), systemAdminDTO));
    }

    @ApiOperation(value = "新增或更新管理员用户信息", notes = "新增或更新管理员用户信息,按照是否传入id进行判断")
    @PostMapping("/saveOrUpdate")
    public ResultBody save(@RequestBody @ApiParam(name="管理员用户对象",value="传入json格式",required=true) SystemAdminDTO dto) throws Exception{
        return ResultBody.success(systemAdminService.saveAdminInfo(dto));
    }
}
