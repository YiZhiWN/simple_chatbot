package com.yango.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RobotApplication {

    public static void main(String[] args) {

        SpringApplication.run(RobotApplication.class, args);

    }

}
