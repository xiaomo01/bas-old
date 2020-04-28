package com.sxgokit.bas.base;

import com.sxgokit.bas.common.exception.ExceptionBaseErrorInfoInterface;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName ResultCommonEnum.java
 * @Description：用于日常返回异常捕获信息
 * @createTime 2019年10月26日 16:33:00
 */
public enum ResultCommonEnum implements ExceptionBaseErrorInfoInterface {

    // 数据操作错误定义
    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400", "请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401", "请求的数字签名不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503", "服务器正忙，请稍后再试!"),
    REQUEST_METHOD_SUPPORT_ERROR("40001","当前请求方法不支持");
    /**
     * 错误码
     */
    private String resultCode;
    /**
     * 错误描述
     */
    private String resultMsg;

    ResultCommonEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}


