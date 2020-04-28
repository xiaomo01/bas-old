package com.sxgokit.bas.controller.system;

import com.google.common.collect.Lists;
import com.sxgokit.bas.base.DataPool;
import com.sxgokit.bas.base.ResultBody;
import com.sxgokit.bas.controller.BaseController;
import com.sxgokit.bas.entity.dto.system.SystemPermissionDTO;
import com.sxgokit.bas.entity.vo.common.CommonRegionInfoVO;
import com.sxgokit.bas.entity.vo.system.SystemPermissionVO;
import com.sxgokit.bas.service.system.SystemPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemPermissionController.java
 * @createTime 2019年10月26日 02:20:00
 */
@RestController
@RequestMapping("system/permission")
@Api(tags = {"系统权限管理"})
public class SystemPermissionController extends BaseController {

    @Autowired
    private SystemPermissionService systemPermissionService;

    @ApiOperation(value = "权限数据集", notes = "获取权限列表信息")
    @PostMapping(value = "/getList")
    public ResultBody<SystemPermissionDTO> getPageList() {
        List<SystemPermissionVO> list = Lists.newArrayList();
        List<SystemPermissionDTO> sourceDTOlist = systemPermissionService.list();
        List<SystemPermissionVO> sourceVOlist = DataPool.copyList(sourceDTOlist, CommonRegionInfoVO.class);
        SystemPermissionVO.sortList(list, sourceVOlist, (long) 0, true);
        return ResultBody.success(sourceVOlist);
    }

    @ApiOperation(value = "新增或更新权限信息", notes = "新增或更新权限信息,按照是否传入id进行判断")
    @PostMapping("/saveOrUpdate")
    public ResultBody save(@RequestBody @ApiParam(name = "权限对象", value = "传入json格式", required = true) SystemPermissionDTO dto) throws Exception {
        return ResultBody.success(systemPermissionService.savePermissionInfo(dto));
    }

    @ApiOperation(value = "获取权限详情", notes = "通过权限表id获取权限详情")
    @PostMapping(value = "/getById")
    public ResultBody<SystemPermissionDTO> getById(@ApiParam(value = "权限表ID", required = true) long id) {
        return ResultBody.success(systemPermissionService.getById(id));
    }

    @ApiOperation("删除权限信息")
    @PostMapping("/delete")
    public ResultBody delete(@ApiParam(value = "权限表ID", required = true) long id) {
        return ResultBody.success(systemPermissionService.removeById(id));
    }
}
