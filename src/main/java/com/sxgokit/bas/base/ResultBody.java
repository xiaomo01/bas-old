package com.sxgokit.bas.base;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import io.swagger.annotations.ApiModel;
import cn.hutool.core.collection.CollUtil;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sxgokit.bas.common.exception.ExceptionBaseErrorInfoInterface;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName ResultBody.java
 * @Description：
 * @createTime 2019年10月26日 16:39:00
 */
@Data
@ApiModel(value = "响应数据",description = "响应信息")
public class ResultBody<T>{

    @ApiModelProperty("响应代码")
    private String code;

    @ApiModelProperty("响应消息")
    private String message;

    @ApiModelProperty("响应结果")
    private T result;

    /**
     * 成功
     */
    public static ResultBody success() {
        return success(null);
    }

    /**
     * 构造分页返回参数
     */
    public static ResultBody success(IPage iPage, Class modelClass) {
        ResultBody rb = new ResultBody();
        rb.setCode(ResultCommonEnum.SUCCESS.getResultCode());
        rb.setMessage(ResultCommonEnum.SUCCESS.getResultMsg());
        Map<String, Object> resultMap = CollUtil.newHashMap();
        resultMap.put("page", new CPage(iPage));
        resultMap.put("data", DataPool.copyList(iPage.getRecords(), modelClass));
        rb.setResult(resultMap);
        return rb;
    }

    /**
     * 成功
     */
    public static ResultBody success(Object data) {
        ResultBody rb = new ResultBody();
        rb.setCode(ResultCommonEnum.SUCCESS.getResultCode());
        rb.setMessage(ResultCommonEnum.SUCCESS.getResultMsg());
        rb.setResult(data);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(ExceptionBaseErrorInfoInterface errorInfo) {
        ResultBody rb = new ResultBody();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(String code, String message) {
        ResultBody rb = new ResultBody();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(String message) {
        ResultBody rb = new ResultBody();
        rb.setCode("-1");
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }
}
