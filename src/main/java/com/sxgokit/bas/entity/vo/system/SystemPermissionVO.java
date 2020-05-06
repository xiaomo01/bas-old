package com.sxgokit.bas.entity.vo.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="systemPermission对象",description="菜单权限信息")
public class SystemPermissionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", example = "test_id")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "菜单名称", example = "test_permissionName")
    private String permissionName;

    /**
     * 上级编码
     */
    @ApiModelProperty(value = "上级编码", example = "test_permissionParentId")
    private Long permissionParentId;

    /**
     * 级别编码
     */
    @ApiModelProperty(value = "级别编码", example = "test_permissionGrade")
    private Integer permissionGrade;

    /**
     * 类型（菜单、功能）
     */
    @ApiModelProperty(value = "菜单类型", example = "test_permissionType")
    private Integer permissionType;

    /**
     * 是否底层菜单（用于判断是否可添加下级菜单）
     */
    @ApiModelProperty(value = "是否底层菜单", example = "test_lastFlag")
    private Integer lastFlag;

    /**
     * 菜单链接
     */
    @ApiModelProperty(value = "菜单链接", example = "test_permissionUrl")
    private String permissionUrl;

    /**
     * 所有功能编码
     */
    @ApiModelProperty(value = "所有功能编码", example = "test_allPermissionIds")
    private String allPermissionIds;

    /**
     * 排序号(升序)
     */
    @ApiModelProperty(value = "排序号(升序)", example = "test_sortNum")
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
