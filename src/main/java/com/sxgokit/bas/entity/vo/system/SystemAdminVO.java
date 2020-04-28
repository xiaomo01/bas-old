package com.sxgokit.bas.entity.vo.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SystemAdminVO.java
 * @Description：
 * @createTime 2019年12月26日 11:28:00
 */
@Data
public class SystemAdminVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 管理员名称
     */
    private String adminName;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String loginPass;

    /**
     * 管理员状态
     */
    private Integer adminState;

    /**
     * 排序号(升序)
     */
    private Integer sortNum;
    /**
     * 头像路径
     */
    private String headImagePath;

    /**
     * 联系方式
     */
    private String adminPhone;

    /**
     * 身份证号
     */
    private String adminCardNo;

    /**
     * 性别
     */
    private Integer adminSex;

    /**
     * 年龄
     */
    private Integer adminAge;

    /**
     * 地址
     */
    private String adminAddress;

    /**
     * 备注
     */
    private String remark;
}
