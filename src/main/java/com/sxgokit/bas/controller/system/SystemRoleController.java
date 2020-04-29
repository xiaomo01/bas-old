package com.sxgokit.bas.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxgokit.bas.base.ResultBody;
import com.sxgokit.bas.controller.BaseController;
import com.sxgokit.bas.entity.dto.system.SystemRoleDTO;
import com.sxgokit.bas.entity.vo.system.SystemRoleVO;
import com.sxgokit.bas.service.system.SystemRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemRoleController.java
 * @createTime 2019年10月26日 02:20:00
 */
@RestController
@RequestMapping("system/role")
@Api(tags={"系统角色管理"})
public class SystemRoleController extends BaseController {

    @Autowired
    private SystemRoleService systemRoleService;

    @ApiOperation(value = "列表分页", notes = "获取系统角色列表信息")
    @PostMapping(value = "/getPageList/{currentPage}/{showSize}")
    public ResultBody<SystemRoleDTO> getPageList(
            @ApiParam(name = "currentPage", value = "当前页码", required = true, defaultValue = "1") @PathVariable int currentPage,
            @ApiParam(name = "showSize", value = "一页展示数" , required = true, defaultValue = "10") @PathVariable int showSize
//            @ApiParam(name = "查询条件前台传参别名", value = "(选填)参数说明", required = true) @RequestParam String paramName,
    ){
        return ResultBody.success(systemRoleService.page(new Page<>(currentPage, showSize), null), SystemRoleVO.class);
    }

    @ApiOperation(value = "新增或更新系统角色信息", notes = "新增或更新系统角色信息,按照是否传入id进行判断")
    @PostMapping("/saveOrUpdate")
    public ResultBody save(@RequestBody @ApiParam(name="系统角色对象",value="传入json格式",required=true) SystemRoleDTO dto) throws Exception{
        return ResultBody.success(systemRoleService.saveRoleInfo(dto));
    }

    @ApiOperation(value = "获取系统角色详情", notes = "通过系统角色表id获取系统角色详情")
    @PostMapping(value = "/getById")
    public ResultBody<SystemRoleDTO> getById(@ApiParam(value = "系统角色表ID", required = true)@PathVariable long id){
        return ResultBody.success(systemRoleService.getById(id));
    }

    @ApiOperation("删除系统角色信息")
    @PostMapping("/delete")
    public ResultBody delete(@ApiParam(value = "系统角色表ID", required = true)@PathVariable long id){
        return ResultBody.success(systemRoleService.removeById(id));
    }
}
