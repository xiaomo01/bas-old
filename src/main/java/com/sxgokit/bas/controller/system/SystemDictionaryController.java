package com.sxgokit.bas.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxgokit.bas.base.ResultBody;
import com.sxgokit.bas.controller.BaseController;
import com.sxgokit.bas.entity.dto.system.SystemDictionaryDTO;
import com.sxgokit.bas.entity.vo.system.SystemDictionaryVO;
import com.sxgokit.bas.service.system.SystemDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemDictionaryController.java
 * @createTime 2019年10月26日 02:20:00
 */
@RestController
@RequestMapping("system/dictionary")
@Api(tags={"数据字典管理"})
public class SystemDictionaryController extends BaseController {

    @Autowired
    private SystemDictionaryService systemDictionaryService;

    @ApiOperation(value = "列表分页", notes = "获取数据字典列表信息")
    @PostMapping(value = "/getPageList/{currentPage}/{showSize}")
    public ResultBody<SystemDictionaryDTO> getPageList(
            @ApiParam(name = "currentPage", value = "当前页码", required = true, defaultValue = "1") @PathVariable int currentPage,
            @ApiParam(name = "showSize", value = "一页展示数" , required = true, defaultValue = "10") @PathVariable int showSize
//            @ApiParam(name = "查询条件前台传参别名", value = "(选填)参数说明", required = true) @RequestParam String paramName,
    ){
        return ResultBody.success(systemDictionaryService.page(new Page<>(currentPage, showSize), null), SystemDictionaryVO.class);
    }

    @ApiOperation(value = "新增或更新数据字典信息", notes = "新增或更新数据字典信息,按照是否传入id进行判断")
    @PostMapping("/saveOrUpdate")
    public ResultBody save(@RequestBody @ApiParam(name="数据字典对象",value="传入json格式",required=true) SystemDictionaryDTO dto){
        return ResultBody.success(systemDictionaryService.saveOrUpdate(dto));
    }

    @ApiOperation(value = "获取数据字典详情", notes = "通过数据字典表id获取数据字典详情")
    @PostMapping(value = "/getById")
    public ResultBody<SystemDictionaryDTO> getById(@ApiParam(value = "数据字典表ID", required = true) long id){
        return ResultBody.success(systemDictionaryService.getById(id));
    }

    @ApiOperation("删除数据字典信息")
    @PostMapping("/delete")
    public ResultBody delete(@ApiParam(value = "数据字典表ID", required = true) long id){
        return ResultBody.success(systemDictionaryService.removeById(id));
    }
}
