package com.sxgokit.bas.base;

import cn.hutool.crypto.digest.DigestUtil;
import com.sxgokit.bas.entity.dto.system.SystemAdminDTO;
import com.sxgokit.bas.entity.vo.system.SystemAdminVO;
import com.sxgokit.bas.util.redis.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Author: token容器
 * Created: 2018/3/19 0019
 */
@Component
@Slf4j
@ConfigurationProperties(prefix = "jwt")
@Data
public class TokenComponent {

    private String secret;

    private long expire;

    private String header;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据用户ID创建token
     * @param identityId
     * @return
     */
    public String createToken(SystemAdminVO adminVO) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        String token = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(adminVO.getAdminId().toString())
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        redisUtil.set(token, adminVO, expire);
        return token;
    }

    /**
     * 获取 Token 中注册信息
     * @param token
     * @return
     */
    public Claims getTokenClaim (String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Token 过期验证
     * @param expirationTime
     * @return
     */
    public boolean isTokenExpired (Date expirationTime) {
        return expirationTime.before(new Date());
    }

    public boolean checkToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        return redisUtil.get(token) != null;
    }

    /**
     * 更新数据库中对应token的时间戳
     */
    public void keepaliveToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return;
        }
        redisUtil.set(token, redisUtil.get(token) , expire);
    }

    /**
     * 获取当前用户
     */
    public SystemAdminDTO getCurrentAppUser(HttpServletRequest request) {
        SystemAdminDTO model = this.getInfoByToken(request);
        if (model != null) {
            return model;
        }
        return null;
    }

    /**
     * 通过token获取登陆信息
     */
    private SystemAdminDTO getInfoByToken(HttpServletRequest request) {
        String authToken = request.getHeader(WebServiceAuthInterceptor.HEAD_AUTH_TOKEN);
        SystemAdminDTO model = (SystemAdminDTO)redisUtil.get(authToken);
        if (model == null || StringUtils.isEmpty(authToken)) {
            return null;
        }
        return model;
    }

    /**
     * 验证用户
     */
    public boolean validAppUser(HttpServletRequest request, String appUserId) {
        SystemAdminDTO model = this.getCurrentAppUser(request);
        boolean effect = false;
        if (model != null) {
            effect = model.getId().equals(appUserId);
        }
        return effect;
    }

    public void clearToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return;
        }
        redisUtil.del(token);
    }

    /**
     * 当用户信息发生改变后,更新缓存
     */
    public void updateToken(String token, SystemAdminDTO model) {
        redisUtil.set(token, model, expire);
    }



    //用于用户首次登陆返回
    @Data
    public static class Token {

        private final String authToken;

        private final String appUser;

    }
}
