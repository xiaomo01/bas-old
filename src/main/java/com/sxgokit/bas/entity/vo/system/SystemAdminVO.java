package com.sxgokit.bas.entity.vo.system;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemAdminVO.java
 * @Description：
 * @createTime 2019年12月26日 11:28:00
 */
@Data
@ApiModel(value="systemAdmin对象",description="用户信息")
public class SystemAdminVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID", name = "adminId", example = "test_adminId")
    private Long adminId;

    @ApiModelProperty(value = "用户登录token", name = "token", example = "test_token")
    private String token;

    @ApiModelProperty(value = "用户名称", name = "adminName", example = "test_adminName")
    private String adminName;

    @ApiModelProperty(value = "登录名称", name = "loginName", example = "test_loginName")
    private String loginName;

    @ApiModelProperty(value = "头像路径", name = "headImagePath", example = "test_headImagePath")
    private String headImagePath;

    @ApiModelProperty(value = "联系方式", name = "adminPhone", example = "test_adminPhone")
    private String adminPhone;

    @ApiModelProperty(value = "性别", name = "adminSex", example = "test_adminSex")
    private Integer adminSex;

    @ApiModelProperty(value = "年龄", name = "adminAge", example = "test_adminAge")
    private Integer adminAge;

    @ApiModelProperty(value = "地址", name = "adminAddress", example = "test_adminAddress")
    private String adminAddress;

    @ApiModelProperty(value = "备注", name = "remark", example = "test_remark")
    private String remark;

    @ApiModelProperty(value="用户关联组织机构ID数组", name="orgIdArray")
    private Long[] orgIdArray;

    @ApiModelProperty(value="用户包含角色ID数组", name="orgIdArray")
    private Long[] roleIdArray;

    @ApiModelProperty(value="菜单权限集合", name="orgIdArray")
    private List<SystemPermissionVO> permissionVOList;
}
