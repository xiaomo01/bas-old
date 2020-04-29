package com.sxgokit.bas.controller.system;

import com.google.common.collect.Lists;
import com.sxgokit.bas.base.DataPool;
import com.sxgokit.bas.base.ResultBody;
import com.sxgokit.bas.controller.BaseController;
import com.sxgokit.bas.entity.dto.system.SystemOrgDTO;
import com.sxgokit.bas.entity.vo.common.CommonRegionInfoVO;
import com.sxgokit.bas.entity.vo.system.SystemOrgVO;
import com.sxgokit.bas.service.system.SystemOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemOrgController.java
 * @createTime 2019年10月26日 02:20:00
 */
@RestController
@RequestMapping("system/org")
@Api(tags = {"组织机构管理"})
public class SystemOrgController extends BaseController {

    @Autowired
    private SystemOrgService systemOrgService;

    @ApiOperation(value = "组织机构数据集", notes = "获取组织机构列表信息")
    @PostMapping(value = "/getList")
    public ResultBody<SystemOrgDTO> getPageList() {
        List<SystemOrgVO> list = Lists.newArrayList();
        List<SystemOrgDTO> sourceDTOlist = systemOrgService.list();
        List<SystemOrgVO> sourceVOlist = DataPool.copyList(sourceDTOlist, SystemOrgVO.class);
        SystemOrgVO.sortList(list, sourceVOlist, (long) 0, true);
        return ResultBody.success(sourceVOlist);
    }

    @ApiOperation(value = "新增或更新组织机构信息", notes = "新增或更新组织机构信息,按照是否传入id进行判断")
    @PostMapping("/saveOrUpdate")
    public ResultBody save(@RequestBody @ApiParam(name = "组织机构对象", value = "传入json格式", required = true) SystemOrgDTO dto) throws Exception {
        return ResultBody.success(systemOrgService.saveOrgInfo(dto));
    }

    @ApiOperation(value = "获取组织机构详情", notes = "通过组织机构表id获取组织机构详情")
    @PostMapping(value = "/getById")
    public ResultBody<SystemOrgDTO> getById(@ApiParam(value = "组织机构表ID", required = true)@PathVariable long id) {
        return ResultBody.success(systemOrgService.getById(id));
    }

    @ApiOperation("删除组织机构信息")
    @PostMapping("/delete")
    public ResultBody delete(@ApiParam(value = "组织机构表ID", required = true)@PathVariable long id) {
        return ResultBody.success(systemOrgService.removeById(id));
    }
}
