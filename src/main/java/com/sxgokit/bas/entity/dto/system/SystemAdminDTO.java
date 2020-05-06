package com.sxgokit.bas.entity.dto.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sxgokit.bas.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther: DuKang
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_admin")
@ApiModel(value="SystemAdmin对象",description="后台用户对象")
public class SystemAdminDTO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 管理员名称
     */
    @ApiModelProperty(value="管理员名称", name="adminName", example="test_adminName" ,required = true)
    private String adminName;

    /**
     * 登录名称
     */
    @ApiModelProperty(value="登录名称", name="loginName", example="test_loginName" ,required = true)
    private String loginName;

    /**
     * 登录密码
     */
    @ApiModelProperty(value="登录密码", name="loginPass", example="test_loginPass" ,required = true)
    private String loginPass;

    /**
     * 管理员状态
     */
    @Builder.Default
    @ApiModelProperty(value="管理员状态(1.启用;2.禁用)", name="adminState", example="test_adminState")
    private Integer adminState = 1;

    /**
     * 排序号(升序)
     */
    @ApiModelProperty(value="排序号(升序)", name="sortNum", example="test_sortNum")
    private Integer sortNum;

    /**
     * 创建用户
     */
    @ApiModelProperty(value="创建用户", hidden = true)
    private Long createUser;

    /**
     * 更新用户
     */
    @ApiModelProperty(value="更新用户", hidden = true)
    private Long updateUser;

}