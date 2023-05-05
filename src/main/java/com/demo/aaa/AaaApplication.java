package com.demo.aaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.demo.aaa.mapper")
public class AaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AaaApplication.class, args);
    }

}
