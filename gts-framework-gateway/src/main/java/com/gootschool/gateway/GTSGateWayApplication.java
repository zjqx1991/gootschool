package com.gootschool.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class GTSGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GTSGateWayApplication.class, args);
    }
}
