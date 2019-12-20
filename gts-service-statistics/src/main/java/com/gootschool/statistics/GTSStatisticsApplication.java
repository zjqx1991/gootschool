package com.gootschool.statistics;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.gootschool.statistics.mapper")
public class GTSStatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GTSStatisticsApplication.class, args);
    }
}
