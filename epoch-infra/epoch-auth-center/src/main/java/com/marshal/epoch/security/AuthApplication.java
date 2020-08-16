package com.marshal.epoch.security;

import com.marshal.epoch.security.annotation.EpochAuthorizationServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Marshal
 * @date 2020/1/15
 *
 */
@MapperScan("com.marshal.epoch.**.mapper")
@EpochAuthorizationServer
@SpringBootApplication
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}