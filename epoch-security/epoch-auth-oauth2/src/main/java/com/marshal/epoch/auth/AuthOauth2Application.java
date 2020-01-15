package com.marshal.epoch.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @auth: Marshal
 * @date: 2020/1/15
 * @desc:
 */
@MapperScan("com.marshal.epoch.**.mapper")
@SpringBootApplication
public class AuthOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(AuthOauth2Application.class, args);
    }

}
