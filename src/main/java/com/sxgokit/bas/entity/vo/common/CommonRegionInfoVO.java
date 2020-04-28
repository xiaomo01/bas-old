package com.sxgokit.bas.entity.vo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: SystemDictionaryVO
 * @Description: 行政区划信息返回
 * @Author: Administrator
 * @Date: 2019/12/30 16:42
 * @Version: 1.0
 */
@Data
public class CommonRegionInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 行政区划编码
     */
    private Integer regionCode;

    /**
     * 行政区划名称
     */
    private String regionName;

    /**
     * 行政区划级别(1省；2市；3区县)
     */
    private Integer regionGrade;

    /**
     * 上级行政区划ID
     */
    private Long regionParentId;

    /**
     * 上级行政区划编码
     */
    private Integer regionParentCode;

    /**
     * 行政区划全部id
     */
    private String allRegionIds;

    /**
     * 行政区划全部编码
     */
    private String allRegionCodes;

    /**
     * 行政区划全部名称
     */
    private String allRegionNames;

    /**
     * 排序号(升序)
     */
    private Integer sortNum;

    /**
     * @Description: 将数据根据父子关系进行数据排序
     * @Author: wgl
     * @Date: 2020/1/2 18:17
     * @Param: [list, sourcelist, parentId, cascade]
     * @return: void
     */
    @JsonIgnore
    public static void sortList(List<CommonRegionInfoVO> list, List<CommonRegionInfoVO> sourcelist,
                                Long parentId, boolean cascade) {
        for (int i = 0; i < sourcelist.size(); i++) {
            CommonRegionInfoVO e = sourcelist.get(i);
            if (e.getRegionParentId() != null
                    && e.getRegionParentId().equals(parentId)) {
                list.add(e);
                if (cascade) {
                    // 判断是否还有子节点, 有则继续获取子节点
                    for (int j = 0; j < sourcelist.size(); j++) {
                        CommonRegionInfoVO child = sourcelist.get(j);
                        if (child.getRegionParentId() != null
                                && child.getRegionParentId().equals(e.getId())) {
                            sortList(list, sourcelist, e.getId(), true);
                            break;
                        }
                    }
                }
            }
        }
    }
}
