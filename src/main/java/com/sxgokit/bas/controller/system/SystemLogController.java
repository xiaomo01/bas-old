package com.sxgokit.bas.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxgokit.bas.base.ResultBody;
import com.sxgokit.bas.controller.BaseController;
import com.sxgokit.bas.entity.dto.system.SystemLogDTO;
import com.sxgokit.bas.entity.vo.system.SystemLogVO;
import com.sxgokit.bas.service.system.SystemLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemLogController.java
 * @createTime 2019年10月26日 02:20:00
 */
@RestController
@RequestMapping("system/log")
@Api(tags={"系统日志管理"})
public class SystemLogController extends BaseController {

    @Autowired
    private SystemLogService systemLogService;

    @ApiOperation(value = "列表分页", notes = "获取系统日志列表信息")
    @PostMapping(value = "/getPageList/{currentPage}/{showSize}")
    public ResultBody<SystemLogDTO> getPageList(
            @ApiParam(name = "currentPage", value = "当前页码", required = true, defaultValue = "1") @PathVariable int currentPage,
            @ApiParam(name = "showSize", value = "一页展示数" , required = true, defaultValue = "10") @PathVariable int showSize
//            @ApiParam(name = "查询条件前台传参别名", value = "(选填)参数说明", required = true) @RequestParam String paramName,
    ){
        return ResultBody.success(systemLogService.page(new Page<>(currentPage, showSize), null), SystemLogVO.class);
    }

    @ApiOperation(value = "新增或更新系统日志信息", notes = "新增或更新系统日志信息,按照是否传入id进行判断")
    @PostMapping("/saveOrUpdate")
    public ResultBody save(@RequestBody @ApiParam(name="系统日志对象",value="传入json格式",required=true) SystemLogDTO dto){
        return ResultBody.success(systemLogService.saveOrUpdate(dto));
    }

    @ApiOperation(value = "获取系统日志详情", notes = "通过系统日志表id获取系统日志详情")
    @PostMapping(value = "/getById")
    public ResultBody<SystemLogDTO> getById(@ApiParam(value = "系统日志表ID", required = true) long id){
        return ResultBody.success(systemLogService.getById(id));
    }

    @ApiOperation("删除系统日志信息")
    @PostMapping("/delete")
    public ResultBody delete(@ApiParam(value = "系统日志表ID", required = true) long id){
        return ResultBody.success(systemLogService.removeById(id));
    }
}
