package org.epoch.security;

import org.epoch.security.annotation.EpochAuthorizationServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Marshal
 * @date 2020/1/15
 */
@MapperScan("org.epoch.**.mapper")
@EpochAuthorizationServer
@SpringBootApplication
public class OAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
    }

}
