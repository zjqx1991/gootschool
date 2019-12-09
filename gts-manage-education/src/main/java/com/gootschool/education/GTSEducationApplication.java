package com.gootschool.education;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.gootschool.common.handler"})
//@ComponentScan("com.gootschool.education.handler")
public class GTSEducationApplication {
    public static void main(String[] args) {
        SpringApplication.run(GTSEducationApplication.class, args);
    }
}
