package com.sxgokit.bas.base;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName BaseEntity.java
 * @Description：
 * @createTime 2019年12月30日 11:33:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "id,新增不需上传", name = "id", example = "null")
    private Long id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间", hidden = true)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value="更新时间", hidden = true)
    private Date updateTime;

    /**
     * 删除状态
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value="删除状态", hidden = true)
    @TableLogic
    private int delFlag;
}
