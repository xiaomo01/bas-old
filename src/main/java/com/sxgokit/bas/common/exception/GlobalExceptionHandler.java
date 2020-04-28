package com.sxgokit.bas.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sxgokit.bas.base.ResultBody;
import com.sxgokit.bas.base.ResultCommonEnum;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName GlobalExceptionHandler.java
 * @Description：全局异常捕获
 * @createTime 2019年10月26日 16:44:00
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResultBody bizExceptionHandler(HttpServletRequest req, BizException e){
        logger.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e){
        logger.error("发生空指针异常！原因是:",e);
        return ResultBody.error(ResultCommonEnum.BODY_NOT_MATCH);
    }

    /**
     * 处理请求方法不支持的异常
     */
    @ExceptionHandler(value =HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, HttpRequestMethodNotSupportedException e){
        logger.error("发生请求方法不支持异常！原因是:",e);
        return ResultBody.error(ResultCommonEnum.REQUEST_METHOD_SUPPORT_ERROR);
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("未知异常！原因是:",e);
        return ResultBody.error(ResultCommonEnum.INTERNAL_SERVER_ERROR);
    }
}


