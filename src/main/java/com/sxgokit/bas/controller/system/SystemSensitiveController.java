package com.sxgokit.bas.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxgokit.bas.base.ResultBody;
import com.sxgokit.bas.controller.BaseController;
import com.sxgokit.bas.entity.dto.system.SystemSensitiveDTO;
import com.sxgokit.bas.entity.vo.system.SystemSensitiveVO;
import com.sxgokit.bas.service.system.SystemSensitiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemSensitiveController.java
 * @createTime 2019年10月26日 02:20:00
 */
@RestController
@RequestMapping("system/sensitive")
@Api(tags={"敏感词管理"})
public class SystemSensitiveController extends BaseController {

    @Autowired
    private SystemSensitiveService systemSensitiveService;

    @ApiOperation(value = "列表分页", notes = "获取敏感词列表信息")
    @PostMapping(value = "/getPageList/{currentPage}/{showSize}")
    public ResultBody<SystemSensitiveDTO> getPageList(
            @ApiParam(name = "currentPage", value = "当前页码", required = true, defaultValue = "1") @PathVariable int currentPage,
            @ApiParam(name = "showSize", value = "一页展示数" , required = true, defaultValue = "10") @PathVariable int showSize
//            @ApiParam(name = "查询条件前台传参别名", value = "(选填)参数说明", required = true) @RequestParam String paramName,
    ){
        return ResultBody.success(systemSensitiveService.page(new Page<>(currentPage, showSize), null), SystemSensitiveVO.class);
    }

    @ApiOperation(value = "新增或更新敏感词信息", notes = "新增或更新敏感词信息,按照是否传入id进行判断")
    @PostMapping("/saveOrUpdate")
    public ResultBody save(@RequestBody @ApiParam(name="敏感词对象",value="传入json格式",required=true) SystemSensitiveDTO dto){
        return ResultBody.success(systemSensitiveService.saveOrUpdate(dto));
    }

    @ApiOperation(value = "获取敏感词详情", notes = "通过敏感词表id获取敏感词详情")
    @PostMapping(value = "/getById")
    public ResultBody<SystemSensitiveDTO> getById(@ApiParam(value = "敏感词表ID", required = true)@PathVariable long id){
        return ResultBody.success(systemSensitiveService.getById(id));
    }

    @ApiOperation("删除敏感词信息")
    @PostMapping("/delete")
    public ResultBody delete(@ApiParam(value = "敏感词表ID", required = true)@PathVariable long id){
        return ResultBody.success(systemSensitiveService.removeById(id));
    }
}
