package com.sxgokit.bas.entity.vo.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: SystemOrgVO
 * @Description: 组织机构表返回
 * @Author: Administrator
 * @Date: 2019/12/30 16:42
 * @Version: 1.0
 */
@Data
public class SystemOrgVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 机构级别
     */
    private Integer orgGrade;

    /**
     * 上级机构编码
     */
    private Long orgParentId;

    /**
     * 所有机构编码
     */
    private String allOrgCodes;

    /**
     * 机构描述
     */
    private String remark;

    /**
     * 排序号(升序)
     */
    private Integer sortNum;

    /**
     * 删除状态
     */
    private Integer delFlag;

    /**
     * @Description: 将数据根据父子关系进行数据排序
     * @Author: wgl
     * @Date: 2020/1/2 18:17
     * @Param: [list, sourcelist, parentId, cascade]
     * @return: void
     */
    @JsonIgnore
    public static void sortList(List<SystemOrgVO> list, List<SystemOrgVO> sourcelist,
                                Long parentId, boolean cascade) {
        for (int i = 0; i < sourcelist.size(); i++) {
            SystemOrgVO e = sourcelist.get(i);
            if (e.getOrgParentId() != null
                    && e.getOrgParentId().equals(parentId)) {
                list.add(e);
                if (cascade) {
                    // 判断是否还有子节点, 有则继续获取子节点
                    for (int j = 0; j < sourcelist.size(); j++) {
                        SystemOrgVO child = sourcelist.get(j);
                        if (child.getOrgParentId() != null
                                && child.getOrgParentId().equals(e.getId())) {
                            sortList(list, sourcelist, e.getId(), true);
                            break;
                        }
                    }
                }
            }
        }
    }

}
