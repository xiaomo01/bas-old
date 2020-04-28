package com.sxgokit.bas.entity.dto.system;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志信息表
 *
 * @author wgl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_log")
@ApiModel(value = "systemLog对象", description = "系统日志信息表对象")
public class SystemLogDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "id,新增不需上传", name = "id", example = "null")
    private Long id;

    /**
     * 操作人id
     */
    @ApiModelProperty(value = "操作人id", name = "optUserId", example = "test_optUserId")
    private Long optUserId;

    /**
     * 功能id
     */
    @ApiModelProperty(value = "功能id", name = "permissionId", example = "test_permissionId")
    private Long permissionId;

    /**
     * 功能说明
     */
    @ApiModelProperty(value = "功能说明", name = "optTheme", example = "test_optTheme")
    private String optTheme;

    /**
     * 具体操作内容
     */
    @ApiModelProperty(value = "具体操作内容", name = "optContent", example = "test_optContent")
    private String optContent;

    /**
     * 操作相关信息id
     */
    @ApiModelProperty(value = "操作相关信息id", name = "infoId", example = "test_infoId")
    private Long infoId;

    /**
     * 操作接口地址
     */
    @ApiModelProperty(value = "操作接口地址", name = "optUrl", example = "test_optUrl")
    private String optUrl;

    /**
     * 操作ip
     */
    @ApiModelProperty(value = "操作ip", name = "optIp", example = "test_optIp")
    private String optIp;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间", name = "optTime", example = "test_optTime")
    private Date optTime;

}

