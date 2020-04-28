package com.sxgokit.bas.base;

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
            String authToken = request.getHeader(HEAD_AUTH_TOKEN);
            if (StringUtils.isEmpty(authToken)) {
                authToken = this.parseTokenFromRequest(request);
            }
            if (tokenComponent.checkToken(authToken)) {
                tokenComponent.keepaliveToken(authToken);
                return true;
            } else {
                try {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    response.getWriter().write(JSONUtil.parse(ResultBody.error(ResultCommonEnum.SIGNATURE_NOT_MATCH)).toString());
                } catch (IOException e) {
                    log.error("Write No Auth message exception.", e);
                }
                return false;
            }
        }
    }
}
