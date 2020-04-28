package com.sxgokit.bas.config.swagger;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.service.ApiInfo;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spring.web.plugins.Docket;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName SwaggerConfig.java
 * @createTime 2019年10月26日 02:14:00
 * 使用Swagger2完成api文档一键输出
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    public static final String SWAGGER_SCAN_ADMIN_PACKAGE = "com.sxgokit.bas.controller.system";
    public static final String SWAGGER_SCAN_ADMIN_PACKAGE_NAME = "system";
    public static final String ADMIN_VERSION = "1.0.0";

    public static final String SWAGGER_SCAN_COMMON_PACKAGE = "com.sxgokit.bas.controller.common";
    public static final String SWAGGER_SCAN_COMMON_PACKAGE_NAME = "common";
    public static final String COMMON_VERSION = "1.0.0";

    public static final String SWAGGER_SCAN_APP_PACKAGE = "com.sxgokit.bas.controller.app";
    public static final String SWAGGER_SCAN_APP_PACKAGE_NAME = "app";
    public static final String APP_VERSION = "1.0.0";

    @Bean
    public Docket createAdminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("系统管理接口")
                .apiInfo(apiAdminInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_ADMIN_PACKAGE))//api接口包扫描路径
                .paths(PathSelectors.regex(".*/"+SWAGGER_SCAN_ADMIN_PACKAGE_NAME+"/.*"))//可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build();
    }

    @Bean
    public Docket createCommonRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("系统公共接口")
                .apiInfo(apiCommonInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_COMMON_PACKAGE))//api接口包扫描路径
                .paths(PathSelectors.regex(".*/"+SWAGGER_SCAN_COMMON_PACKAGE_NAME+"/.*"))//可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build();
    }

    @Bean
    public Docket createAppRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("前端数据接口")
                .apiInfo(apiAppInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_APP_PACKAGE))//api接口包扫描路径
                .paths(PathSelectors.regex(".*/"+SWAGGER_SCAN_APP_PACKAGE_NAME+"/.*"))//可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build();
    }

    private ApiInfo apiAdminInfo() {
        return new ApiInfo("SXGOKIT-BAS项目 Platform API 后台管理系统",//大标题
                initContextInfo(),//简单的描述
                ADMIN_VERSION,//版本
                "服务条款",
                new Contact("SoulCoder", "https://www.showdoc.cc/542096461379123?page_id=3201532584444235", "dukang@sxgok.com"),
                "SXGOKIT-BAS前后分离式框架使用文档",//链接显示文字
                "https://www.showdoc.cc/542096461379123?page_id=3201532584444235"//网站链接
        );
    }

    private ApiInfo apiCommonInfo() {
        return new ApiInfo("SXGOKIT-BAS项目 Platform API 系统公共接口",//大标题
                initContextInfo(),//简单的描述
                COMMON_VERSION,//版本
                "服务条款",
                new Contact("SoulCoder", "https://www.showdoc.cc/542096461379123?page_id=3201532584444235", "dukang@sxgok.com"),
                "SXGOKIT-BAS前后分离式框架使用文档",//链接显示文字
                "https://www.showdoc.cc/542096461379123?page_id=3201532584444235"//网站链接
        );
    }

    private ApiInfo apiAppInfo() {
        return new ApiInfo("SXGOKIT-BAS项目 Platform API 前端信息系统",//大标题
                initContextInfo(),//简单的描述
                APP_VERSION,//版本
                "服务条款",
                new Contact("SoulCoder", "https://www.showdoc.cc/542096461379123?page_id=3201532584444235", "dukang@sxgok.com"),
                "SXGOKIT-BAS前后分离式框架使用文档",//链接显示文字
                "https://www.showdoc.cc/542096461379123?page_id=3201532584444235"//网站链接
        );
    }

    private String initContextInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("SXGOKIT-BASR设计遵循REST-API设计模式，使用当前流行的组件Springboot+SpringMVC+MybatisPlus+Swagger2完成后台api框架完成框架搭建。");
        return sb.toString();
    }

    //添加ResourceHandler
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
