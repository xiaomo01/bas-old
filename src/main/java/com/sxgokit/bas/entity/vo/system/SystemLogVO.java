package com.sxgokit.bas.entity.vo.system;

import java.io.Serializable;
import lombok.Data;
import java.util.Date;

/**
 *  系统日志信息表
 * @author wgl 2019-12-31
 */
@Data
public class SystemLogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 操作人id
     */
    private Long optUserId;

    /**
     * 功能id
     */
    private Long permissionId;

    /**
     * 功能说明
     */
    private String optTheme;

    /**
     * 具体操作内容
     */
    private String optContent;

    /**
     * 操作相关信息id
     */
    private Long infoId;

    /**
     * 操作接口地址
     */
    private String optUrl;

    /**
     * 操作ip
     */
    private String optIp;

    /**
     * 操作时间
     */
    private Date optTime;

}
