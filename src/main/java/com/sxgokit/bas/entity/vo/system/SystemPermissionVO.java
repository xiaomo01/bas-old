package com.sxgokit.bas.entity.vo.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: SystemPermissionVO
 * @Description: 权限表返回
 * @Author: Administrator
 * @Date: 2019/12/30 16:42
 * @Version: 1.0
 */
@Data
public class SystemPermissionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String permissionName;

    /**
     * 上级编码
     */
    private Long permissionParentId;

    /**
     * 级别编码
     */
    private Integer permissionGrade;

    /**
     * 类型（菜单、功能）
     */
    private Integer permissionType;

    /**
     * 是否底层菜单（用于判断是否可添加下级菜单）
     */
    private Integer lastFlag;

    /**
     * 菜单链接
     */
    private String permissionUrl;

    /**
     * 所有功能编码
     */
    private String allPermissionIds;

    /**
     * 排序号(升序)
     */
    private Integer sortNum;

    /**
     * 描述
     */
    private String remark;

    /**
     * @Description: 将数据根据父子关系进行数据排序
     * @Author: wgl
     * @Date: 2020/1/2 18:17
     * @Param: [list, sourcelist, parentId, cascade]
     * @return: void
     */
    @JsonIgnore
    public static void sortList(List<SystemPermissionVO> list, List<SystemPermissionVO> sourcelist,
                                Long parentId, boolean cascade) {
        for (int i = 0; i < sourcelist.size(); i++) {
            SystemPermissionVO e = sourcelist.get(i);
            if (e.getPermissionParentId() != null
                    && e.getPermissionParentId().equals(parentId)) {
                list.add(e);
                if (cascade) {
                    // 判断是否还有子节点, 有则继续获取子节点
                    for (int j = 0; j < sourcelist.size(); j++) {
                        SystemPermissionVO child = sourcelist.get(j);
                        if (child.getPermissionParentId() != null
                                && child.getPermissionParentId().equals(e.getId())) {
                            sortList(list, sourcelist, e.getId(), true);
                            break;
                        }
                    }
                }
            }
        }
    }
}
