package com.sxgokit.bas.config.json;

import java.util.List;
import java.util.ArrayList;
import java.nio.charset.Charset;
import org.springframework.http.MediaType;
import org.springframework.context.annotation.Bean;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName FastJsonConfiguration.java
 * @Description：将springboot默认的HttpMessageConverter替换为阿里的FastJson转换器MappingJackson2HttpMessageConverter
 * 为解决json参数上传及响应过程中转换异常的问题
 * @createTime 2019年10月27日 16:58:00
 */
@Configuration
public class FastJsonConfiguration {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        List<MediaType> fastMediaTypes = new ArrayList<>();
        // 处理中文乱码问题
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        //设置返回方式
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue);
        // 设置时间格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        // 在转换器中添加配置信息
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        HttpMessageConverter converter = fastJsonHttpMessageConverter;
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setDefaultCharset(Charset.forName("UTF-8"));
        stringConverter.setSupportedMediaTypes(fastMediaTypes);
        return new HttpMessageConverters(stringConverter, converter);
    }
}


