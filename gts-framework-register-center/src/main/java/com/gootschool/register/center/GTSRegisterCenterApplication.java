package com.gootschool.register.center;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GTSRegisterCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(GTSRegisterCenterApplication.class, args);
    }
}
