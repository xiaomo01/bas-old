package com.sxgokit.bas.controller.system;

import com.sxgokit.bas.entity.vo.system.SystemConfigVO;
import io.swagger.annotations.Api;
import com.sxgokit.bas.base.ResultBody;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
import com.sxgokit.bas.controller.BaseController;
import org.springframework.web.bind.annotation.*;
import com.sxgokit.bas.entity.dto.system.SystemConfigDTO;
import com.sxgokit.bas.service.system.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemConfigController.java
 * @createTime 2019年10月26日 02:20:00
 */
@RestController
@RequestMapping("system/config")
@Api(tags={"系统配置管理"})
public class SystemConfigController extends BaseController {

    @Autowired
    private SystemConfigService systemConfigService;

    @ApiOperation(value = "列表分页", notes = "获取系统配置列表信息")
    @PostMapping(value = "/getPageList/{currentPage}/{showSize}")
    public ResultBody<SystemConfigDTO> getPageList(
            @ApiParam(name = "currentPage", value = "当前页码", required = true, defaultValue = "1") @PathVariable int currentPage,
            @ApiParam(name = "showSize", value = "一页展示数" , required = true, defaultValue = "10") @PathVariable int showSize
//            @ApiParam(name = "查询条件前台传参别名", value = "(选填)参数说明", required = true) @RequestParam String paramName,
    ){
        return ResultBody.success(systemConfigService.page(new Page<>(currentPage, showSize), null), SystemConfigVO.class);
    }

    @ApiOperation(value = "新增或更新系统配置信息", notes = "新增或更新配置信息,按照是否传入id进行判断")
    @PostMapping("/saveOrUpdate")
    public ResultBody save(@RequestBody @ApiParam(name="系统配置对象",value="传入json格式",required=true) SystemConfigDTO dto){
        return ResultBody.success(systemConfigService.saveOrUpdate(dto));
    }

    @ApiOperation(value = "获取系统配置详情", notes = "通过系统配置表id获取系统配置详情")
    @PostMapping(value = "/getById")
    public ResultBody<SystemConfigDTO> getById(@ApiParam(value = "系统配置表ID", required = true) long id){
        return ResultBody.success(systemConfigService.getById(id));
    }

    @ApiOperation("删除系统配置信息")
    @PostMapping("/delete")
    public ResultBody delete(@ApiParam(value = "系统配置表ID", required = true) long id){
        return ResultBody.success(systemConfigService.removeById(id));
    }
}
