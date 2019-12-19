package com.gootschool.usercenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.gootschool.usercenter.mapper")
public class GTSUserCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(GTSUserCenterApplication.class, args);
    }
}
