package com.sxgokit.bas.common.exception;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName ExceptionBaseErrorInfoInterface.java
 * @Description：此接口用于返回码枚举使用
 * @createTime 2019年10月26日 16:26:00
 */

public interface ExceptionBaseErrorInfoInterface {

    /** 错误码*/
    String getResultCode();

    /** 错误描述*/
    String getResultMsg();
}

