package com.sxgokit.bas.config.mybatisplus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * @author dukang
 * @version 1.0.0
 * @ClassName MybatisPlusConfig.java
 * @createTime 2019年10月21日 15:58:00
 */
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}