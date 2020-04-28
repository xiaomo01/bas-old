package com.sxgokit.bas.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses({
        @ApiResponse(code=400,message="请求的数据格式不符!"),
        @ApiResponse(code=401,message="请求的数字签名不匹配!"),
        @ApiResponse(code=404,message="未找到该资源!"),
        @ApiResponse(code=500,message="服务器内部错误!"),
        @ApiResponse(code=503,message="服务器正忙，请稍后再试!"),
        @ApiResponse(code=40001,message="当前请求方法不支持!")
})
public class BaseController {

    /**
     * 重定向
     **/
    protected final static String REQUEST_REDIRECT = "redirect:";
}
