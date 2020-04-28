package com.sxgokit.bas.entity.dto.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther: DuKang
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_admin_info")
@ApiModel(value="systemAdminInfo对象",description="后台用户附属信息对象")
public class SystemAdminInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "id,新增不需上传", name = "id")
    private Long id;

    /**
     * 管理员id
     */
    @ApiModelProperty(value = "管理员id", name = "adminId", example = "test_adminId")
    private Long adminId;

    /**
     * 头像路径
     */
    @ApiModelProperty(value = "头像路径", name = "headImagePath", example = "test_headImagePath")
    private String headImagePath;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式", name = "adminPhone", example = "test_adminPhone")
    private String adminPhone;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号", name = "adminCardNo", example = "test_adminCardNo")
    private String adminCardNo;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", name = "adminSex", example = "test_adminSex")
    private Integer adminSex;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄", name = "adminAge", example = "test_adminAge")
    private Integer adminAge;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址", name = "adminAddress", example = "test_adminAddress")
    private String adminAddress;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", example = "test_remark")
    private String remark;

    /**
     * 创建用户
     */
    @ApiModelProperty(value="创建用户", hidden = true)
    private Long createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间", hidden = true)
    private Date createTime;

    /**
     * 更新用户
     */
    @ApiModelProperty(value="更新用户", hidden = true)
    private Long updateUser;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间", hidden = true)
    private Date updateTime;
}