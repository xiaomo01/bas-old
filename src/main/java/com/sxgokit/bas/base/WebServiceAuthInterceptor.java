package com.sxgokit.bas.base;

import com.sxgokit.bas.common.exception.BizException;
import io.jsonwebtoken.Claims;
import lombok.Data;
import java.io.IOException;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Author: dukang
 * Created: 2018/3/19
 */
@Slf4j
@Data
public class WebServiceAuthInterceptor extends HandlerInterceptorAdapter {

    private TokenComponent tokenComponent;

    public static final String HEAD_AUTH_TOKEN = "Auth-Token";

    public WebServiceAuthInterceptor(TokenComponent tokenComponent) {
        this.tokenComponent = tokenComponent;
    }

    public String parseTokenFromRequest(HttpServletRequest request) {
        String sign = request.getParameter("sign");
        if (StringUtils.isNotEmpty(sign)) {
            return StringUtils.substringAfter(sign, ":");
        } else {
            return null;
        }
    }

    public String getTokenByRequest(HttpServletRequest request){
        return parseTokenFromRequest(request);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMethod method = (HandlerMethod) handler;
        OpenAnnotationMethod annotationMethod = method.getMethodAnnotation(OpenAnnotationMethod.class);
        OpenAnnotationController annotationForController = method.getMethod().getDeclaringClass().getAnnotation(OpenAnnotationController.class);
        if (annotationMethod != null && annotationMethod.value()) {
            return true;
        }else if(annotationForController != null && annotationForController.value() ){
            return true;
        } else {
            // Token 验证
            String token = request.getHeader(tokenComponent.getHeader());
            if(org.springframework.util.StringUtils.isEmpty(token)){
                token = request.getParameter(tokenComponent.getHeader());
            }
            if(org.springframework.util.StringUtils.isEmpty(token)){
                throw new BizException(ResultCommonEnum.NO_TOKEN);
            }
            Claims claims = tokenComponent.getTokenClaim(token);
            if(claims == null || tokenComponent.isTokenExpired(claims.getExpiration())){
                throw new BizException(ResultCommonEnum.TOKEN_TIMEOUT);
            }
            //设置 userId 用户身份ID
            request.setAttribute("userId", claims.getSubject());
            return true;
        }
    }
}
