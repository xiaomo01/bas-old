package com.sxgokit.bas.entity.dto.common;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 行政区划信息表
 *
 * @author wgl 2019-12-31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("common_region_info")
@ApiModel(value = "commonRegionInfo对象", description = "行政区划信息表对象")
public class CommonRegionInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "id,新增不需上传", name = "id")
    private Long id;

    /**
     * 行政区划编码
     */
    @ApiModelProperty(value = "行政区划编码", name = "regionCode", example = "test_regionCode", required = true)
    private Integer regionCode;

    /**
     * 行政区划名称
     */
    @ApiModelProperty(value = "行政区划名称", name = "regionName", example = "test_regionName", required = true)
    private String regionName;

    /**
     * 行政区划级别(1省；2市；3区县)
     */
    @ApiModelProperty(value = "行政区划级别(1省；2市；3区县)", hidden = true)
    private Integer regionGrade;

    /**
     * 上级行政区划ID
     */
    @ApiModelProperty(value = "上级行政区划ID", name = "regionParentId", example = "test_regionParentId", required = true)
    private Long regionParentId;

    /**
     * 上级行政区划编码
     */
    @ApiModelProperty(value = "上级行政区划编码", hidden = true)
    private Integer regionParentCode;

    /**
     * 行政区划全部id
     */
    @ApiModelProperty(value = "行政区划全部id", hidden = true)
    private String allRegionIds;

    /**
     * 行政区划全部编码
     */
    @ApiModelProperty(value = "行政区划全部编码", hidden = true)
    private String allRegionCodes;

    /**
     * 行政区划全部名称
     */
    @ApiModelProperty(value = "行政区划全部名称", hidden = true)
    private String allRegionNames;

    /**
     * 排序号(升序)
     */
    @ApiModelProperty(value = "排序号(升序)", name = "sortNum", example = "test_sortNum")
    private Integer sortNum;

}
