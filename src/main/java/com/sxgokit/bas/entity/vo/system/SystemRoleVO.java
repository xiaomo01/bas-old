package com.sxgokit.bas.entity.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SystemRoleVO
 * @Description: 系统角色表返回
 * @Author: Administrator
 * @Date: 2019/12/30 16:42
 * @Version: 1.0
 */
@Data
public class SystemRoleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 排序号(升序)
     */
    private Integer sortNum;

    /**
     * 角色描述
     */
    private String remark;
}
