package com.gootschool.usercenter.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    private final static String API_VERSION = "1.0";
    private final static String API_AUTHOR = "RevanWang";
    private final static String API_URL = "http://atguigu.com";
    private final static String API_AUTHOR_EMAIL = "zjqx1991@163.com";

    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("用户中心管理API文档")
                .description("本文档描述了用户中心微服务接口定义")
                .version(API_VERSION)
                .contact(new Contact(API_AUTHOR, API_URL, API_AUTHOR_EMAIL))
                .build();
    }

}
