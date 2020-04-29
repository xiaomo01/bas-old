package com.sxgokit.bas.controller.common;

import com.google.common.collect.Lists;
import com.sxgokit.bas.base.DataPool;
import com.sxgokit.bas.base.ResultBody;
import com.sxgokit.bas.controller.BaseController;
import com.sxgokit.bas.entity.dto.common.CommonRegionInfoDTO;
import com.sxgokit.bas.entity.vo.common.CommonRegionInfoVO;
import com.sxgokit.bas.service.common.CommonRegionInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName CommonRegionInfoController.java
 * @createTime 2019年10月26日 02:20:00
 */
@RestController
@RequestMapping("common/commonRegionInfo")
@Api(tags = {"行政区划管理"})
public class CommonRegionInfoController extends BaseController {

    @Autowired
    private CommonRegionInfoService commonRegionInfoService;

    @ApiOperation(value = "行政区划数据集", notes = "获取行政区划列表信息")
    @PostMapping(value = "/getList")
    public ResultBody<CommonRegionInfoDTO> getPageList() {
        List<CommonRegionInfoVO> list = Lists.newArrayList();
        List<CommonRegionInfoDTO> sourceDTOlist = commonRegionInfoService.list();
        List<CommonRegionInfoVO> sourceVOlist = DataPool.copyList(sourceDTOlist, CommonRegionInfoVO.class);
        CommonRegionInfoVO.sortList(list, sourceVOlist, (long) 0, true);
        return ResultBody.success(sourceVOlist);
    }

    @ApiOperation(value = "新增或更新行政区划信息", notes = "新增或更新行政区划信息,按照是否传入id进行判断")
    @PostMapping("/saveOrUpdate")
    public ResultBody save(@RequestBody @ApiParam(name = "行政区划对象", value = "传入json格式", required = true) CommonRegionInfoDTO dto) throws Exception {
        return ResultBody.success(commonRegionInfoService.saveRegionInfo(dto));
    }

    @ApiOperation(value = "获取行政区划详情", notes = "通过行政区划表id获取行政区划详情")
    @PostMapping(value = "/getById")
    public ResultBody<CommonRegionInfoDTO> getById(@ApiParam(value = "行政区划表ID", required = true)@PathVariable long id) {
        return ResultBody.success(commonRegionInfoService.getById(id));
    }

    @ApiOperation("删除行政区划信息")
    @PostMapping("/delete")
    public ResultBody delete(@ApiParam(value = "行政区划表ID", required = true)@PathVariable long id) {
        return ResultBody.success(commonRegionInfoService.removeById(id));
    }
}
