package com.sxgokit.bas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "com.sxgokit.bas.mapper")
@EnableTransactionManagement
public class BasApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasApplication.class, args);
    }

}
